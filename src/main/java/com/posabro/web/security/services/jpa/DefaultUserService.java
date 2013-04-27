/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.web.security.domain.Role;
import com.posabro.web.security.domain.User;
import com.posabro.web.security.domain.Token;
import com.posabro.web.security.repository.UserRepository;
import com.posabro.web.security.services.DeliveryBoy;
import com.posabro.web.security.services.GroupService;
import com.posabro.web.security.services.RoleService;
import com.posabro.web.security.services.UserService;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of <code>UserService<code>
 * 
 * @author Carlos Juarez
 */
@Service("userService")
@Repository
@Transactional
public class DefaultUserService implements UserService {

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultUserService.class);
    
    /**
     * The userRepository
     */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * The groupService
     */
    @Autowired
    private GroupService groupService;
    
    /**
     * The roleService
     */
    @Autowired
    private RoleService roleService;
    
    /**
     * The deliveryBoy
     */
    @Autowired
    private DeliveryBoy deliveryBoy;
    
    /**
     * The entityManager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @see UserService#queryPageByStringPattern(java.lang.String, org.springframework.data.domain.Pageable) 
     * 
     * @param pattern
     * @param pageable
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public Page<User> queryPageByStringPattern(String pattern, Pageable pageable) {
        return userRepository.findByNameContainingOrEmailContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(pattern, pattern, pattern, pattern, pageable);
    }

    /**
     * @see UserService#queryPageByDatePattern(java.util.Date, org.springframework.data.domain.Pageable) 
     * 
     * @param datePattern
     * @param pageable
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public Page<User> queryPageByDatePattern(Date datePattern, Pageable pageable) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datePattern);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date upToDate = calendar.getTime();
        return userRepository.findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(datePattern, upToDate, datePattern, upToDate, pageable);
    }

    /**
     * @see UserService#registerUser(com.posabro.web.security.domain.User) 
     * The user about to be registered is going to receive an email to verify that he really owns
     * the email address
     * 
     * @param user 
     */
    @Override
    public void registerUser(User user) {
        user.setVerifiedEmail(false);
        saveUser(user);
        Token token = createAndSaveEmailToken(user.getName());
        deliveryBoy.sendEmailVerification(user.getName(), user.getEmail(), token.getId());
    }

    /**
     * @see UserService#registerGuest(com.posabro.web.security.domain.User) 
     * When registering a guest his status is set to ENABLED and default role is assigned to him
     * 
     * @param user 
     */
    @Override
    public void registerGuest(User user) {
        Role defaultRole = roleService.getDefaultRole();
        user.setRoles(Arrays.asList(defaultRole));
        user.setStatus(User.Status.ENABLED);
        registerUser(user);
    }

    /**
     * @see UserService#updateUser(com.posabro.web.security.domain.User) 
     * user password is encrypted and verification email address is sent
     * @param user 
     */
    @Override
    public void updateUser(User user) {
        logger.debug("about to update : " + user);
        user.setPassword(encryptPassword(user.getPassword()));
        User findOne = userRepository.findOne(user.getName());
        if (!user.getEmail().equals(findOne.getEmail())) {
            user.setVerifiedEmail(false);
            Token token = createAndSaveEmailToken(user.getName());
            deliveryBoy.sendEmailVerification(user.getName(), user.getEmail(), token.getId());
        }
        userRepository.save(user);
    }

    /**
     * @see UserService#removeUser(java.lang.String) 
     * @param name 
     */
    @Override
    public void removeUser(String name) {
        if (groupService.isGivenMemberNameBeingUsed(name)) {
            throw new JpaSystemException(new PersistenceException(String.format("user %s is used by some groups",name)));
        }
        userRepository.delete(name);
    }

    /**
     * @see UserService#getAllUsers()
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    /**
     * @see UserService#findUser(java.lang.String)
     * 
     * @param name
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public User findUser(String name) {
        return userRepository.findOne(name);
    }
    
    /**
     * @see UserService#verifyEmailAddress(java.lang.String, java.lang.String) 
     * If the token is not found by the repository a <code>JpaSystemException</code> exception is thrown
     * If the user is not found a <code>JpaSystemException</code> exception is thrown
     * 
     * @param tokenId
     * @param userName
     * @return 
     */
    @Override
    public User verifyEmailAddress(String tokenId, String userName) {
        Token token = em.find(Token.class, tokenId);
        if (token == null || !token.getUserName().equals(userName)) {
            throw new JpaSystemException(new PersistenceException(String.format("user %s doesn't have a confirmation token so can't be confirmed",userName)));
        } else {
            User userFound = userRepository.findOne(userName);
            if (userFound == null) {
                throw new JpaSystemException(new PersistenceException(String.format("user %s doesn't exist so can't be confirmed",userName)));
            }
            userFound.setVerifiedEmail(true);
            return userRepository.save(userFound);
        }
    }

    /**
     * @see UserService#generateTempPassword(java.lang.String, java.lang.String) 
     * If emailAddress does not match with the email address of the user or de email address is not verified
     * a <code>JpaSystemException</code> exception is thrown.
     * If the user has a status different to DISABLED a <code>JpaSystemException</code> exception is thrown.
     * 
     * @param userName
     * @param emailAddress 
     */
    @Override
    public void generateTempPassword(String userName, String emailAddress) {
        User userFound = userRepository.findOne(userName);
        if (!userFound.getEmail().equals(emailAddress)) {
            throw new JpaSystemException(new PersistenceException("user and email address given do not match each other"));
        }
        if(userFound.getStatus().equals(User.Status.DISABLED)){
            throw new JpaSystemException(new PersistenceException("it cannot generate a temp password as this user is disabled"));
        }
        if(!userFound.isVerifiedEmail()){
            throw new JpaSystemException(new PersistenceException("it cannot generate a temp password as this email address has not been verified"));
        }
        Query query = em.createQuery("select u from Token u where u.type='TEMP_PASSWORD' and userName = :userName");
        query.setParameter("userName", userName);
        List<Token> tokenFound = query.getResultList();
        if (!tokenFound.isEmpty()) {
            logger.info("a previous temp password token for the user %s will be removed",userName);
            em.remove(tokenFound.get(0));
        }
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String tokenId = encoder.encodePassword(userName + time, null);
        Token newToken = new Token(tokenId, userName);
        newToken.setType(Token.Type.TEMP_PASSWORD);
        userFound.setStatus(User.Status.TEMP_PASSWORD);
        char[] uuid = UUID.randomUUID().toString().substring(0, 9).toCharArray();
        userFound.setPassword(uuid);
        updateUser(userFound);
        em.persist(newToken);
        deliveryBoy.sendTempPassword(userName, emailAddress, tokenId, uuid);
        logger.info(String.format("temp password has been sent to %s",emailAddress));
    }

    /**
     * @see UserService#isVerifiedTempPassword(java.lang.String, java.lang.String) 
     * 
     * @param userName
     * @param tokenId
     * @return 
     */
    @Override
    public boolean isVerifiedTempPassword(String userName, String tokenId) {
        Token token = em.find(Token.class, tokenId);
        return !(token == null || !token.getUserName().equals(userName));
    }

    /**
     * @see UserService#retrievePassword(java.lang.String, char[], char[], java.lang.String) 
     * If the token does not match with the existing token for the user given a <code>JpaSystemException</code> exception is thrown
     * If the temp password does not match with the user password a <code>JpaSystemException</code> exception is thrown
     * The user status is changed to enabled and the new password is set
     * 
     * @param userName
     * @param tempPassword
     * @param newPassword
     * @param tokenId 
     */
    @Override
    public void retrievePassword(String userName, char[] tempPassword, char[] newPassword, String tokenId) {
        if (!isVerifiedTempPassword(userName, tokenId)) {
            throw new JpaSystemException(new PersistenceException("This password has already been retrieved"));
        }
        User userFound = userRepository.findOne(userName);
        if(!Arrays.equals(encryptPassword(tempPassword),userFound.getPassword())){
            throw new JpaSystemException(new PersistenceException("The temp password is incorrect"));
        }
        userFound.setStatus(User.Status.ENABLED);
        userFound.setPassword(newPassword);
        updateUser(userFound);
        Token token = em.find(Token.class, tokenId);
        em.remove(token);
    }
    
    /**
     * Saves a user if the user already exists a <code>JpaSystemException</code> exception is thrown
     * 
     * @param user - the user to save
     */
    protected void saveUser(User user) {
        logger.debug("about to save : " + user);
        user.setPassword(encryptPassword(user.getPassword()));
        if (!userRepository.exists(user.getName())) {
            userRepository.save(user);
        } else {
            throw new JpaSystemException(new PersistenceException("user " + user.getName() + " already exists"));
        }
    }

    /**
     * Encrypts password by using MD5
     * 
     * @param password - password to encrypt
     * @return the password encrypted
     */
    protected char[] encryptPassword(char[] password) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(new String(password), null);
        return hashedPass.toCharArray();
    }

    /**
     * Creates an save a token for a user given
     * 
     * @param userName - the user name for whom the token will be associated
     * @return the token created
     */
    private Token createAndSaveEmailToken(String userName) {
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String tokenId = encoder.encodePassword(userName + time, null);
        Token token = new Token(tokenId, userName);
        token.setType(Token.Type.EMAIL);
        em.persist(token);
        return token;
    }

}
