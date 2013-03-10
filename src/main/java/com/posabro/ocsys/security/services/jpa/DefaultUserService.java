/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.domain.UserKey;
import com.posabro.ocsys.security.repository.UserRepository;
import com.posabro.ocsys.security.services.DeliveryBoy;
import com.posabro.ocsys.security.services.GroupService;
import com.posabro.ocsys.security.services.RoleService;
import com.posabro.ocsys.security.services.UserService;
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
 *
 * @author Carlos Juarez
 */
@Service("userService")
@Repository
@Transactional
public class DefaultUserService implements UserService {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultUserService.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GroupService groupService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DeliveryBoy deliveryBoy;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Page<User> queryPageByStringPattern(String pattern, Pageable pageable) {
        return userRepository.findByNameContainingOrEmailContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(pattern, pattern, pattern, pattern, pageable);
    }

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

    @Override
    public void registerUser(User user) {
        user.setVerifiedEmail(false);
        saveUser(user);
        UserKey key = saveEmailConfirmKey(user.getName());
        deliveryBoy.sendEmailVerification(user.getName(), user.getEmail(), key.getKey());
    }

    @Override
    public void registerGuest(User user) {
        Role defaultRole = roleService.getDefaultRole();
        user.setRoles(Arrays.asList(defaultRole));
        user.setStatus(User.Status.ENABLED);
        registerUser(user);
    }

    @Override
    public void updateUser(User user) {
        logger.debug("about to update : " + user);
        user.setPassword(encryptPassword(user.getPassword()));
        User findOne = userRepository.findOne(user.getName());
        if (!user.getEmail().equals(findOne.getEmail())) {
            user.setVerifiedEmail(false);
            UserKey key = saveEmailConfirmKey(user.getName());
            deliveryBoy.sendEmailVerification(user.getName(), user.getEmail(), key.getKey());
        }
        userRepository.save(user);
    }

    @Override
    public void removeUser(String name) {
        if (groupService.isGivenMemberNameBeingUsed(name)) {
            throw new JpaSystemException(new PersistenceException(String.format("user %s is used by some groups",name)));
        }
        userRepository.delete(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(String name) {
        return userRepository.findOne(name);
    }
    
    @Override
    public User confirmEmailAddress(String key, String userName) {
        UserKey userKey = em.find(UserKey.class, key);
        if (userKey == null || !userKey.getUserName().equals(userName)) {
            throw new JpaSystemException(new PersistenceException(String.format("user %s doesn't have a confirmation key so can't be confirmed",userName)));
        } else {
            User userFound = userRepository.findOne(userName);
            if (userFound == null) {
                throw new JpaSystemException(new PersistenceException(String.format("user %s doesn't exist so can't be confirmed",userName)));
            }
            userFound.setVerifiedEmail(true);
            return userRepository.save(userFound);
        }
    }

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
        Query query = em.createQuery("select u from UserKey u where u.type='TEMP_PASSWORD' and userName = :userName");
        query.setParameter("userName", userName);
        List<UserKey> userKeyFound = query.getResultList();
        if (!userKeyFound.isEmpty()) {
            logger.info("a previous temp password key for the user %s will be removed",userName);
            em.remove(userKeyFound.get(0));
        }
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String key = encoder.encodePassword(userName + time, null);
        UserKey newUserKey = new UserKey(key, userName);
        newUserKey.setType(UserKey.Type.TEMP_PASSWORD);
        userFound.setStatus(User.Status.TEMP_PASSWORD);
        char[] uuid = UUID.randomUUID().toString().substring(0, 9).toCharArray();
        userFound.setPassword(uuid);
        updateUser(userFound);
        em.persist(newUserKey);
        deliveryBoy.sendTempPassword(userName, emailAddress, key, uuid);
        logger.info(String.format("temp password has been sent to %s",emailAddress));
    }

    @Override
    public boolean isVerifiedTempPassword(String userName, String key) {
        UserKey userKeyFound = em.find(UserKey.class, key);
        return !(userKeyFound == null || !userKeyFound.getUserName().equals(userName));
    }

    @Override
    public void retrievePassword(String userName, char[] tempPassword, char[] newPassword, String key) {
        if (!isVerifiedTempPassword(userName, key)) {
            throw new JpaSystemException(new PersistenceException("This password has already been retrieved"));
        }
        User userFound = userRepository.findOne(userName);
        if(!Arrays.equals(encryptPassword(tempPassword),userFound.getPassword())){
            throw new JpaSystemException(new PersistenceException("The temp password is incorrect"));
        }
        userFound.setStatus(User.Status.ENABLED);
        userFound.setPassword(newPassword);
        updateUser(userFound);
        UserKey keyFound = em.find(UserKey.class, key);
        em.remove(keyFound);
    }
    
    protected void saveUser(User user) {
        logger.debug("about to save : " + user);
        user.setPassword(encryptPassword(user.getPassword()));
        if (!userRepository.exists(user.getName())) {
            userRepository.save(user);
        } else {
            throw new JpaSystemException(new PersistenceException("user " + user.getName() + " already exists"));
        }
    }

    protected char[] encryptPassword(char[] password) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(new String(password), null);
        return hashedPass.toCharArray();
    }

    private UserKey saveEmailConfirmKey(String userName) {
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String key = encoder.encodePassword(userName + time, null);
        UserKey userKey = new UserKey(key, userName);
        userKey.setType(UserKey.Type.EMAIL);
        em.persist(userKey);
        return userKey;
    }

}
