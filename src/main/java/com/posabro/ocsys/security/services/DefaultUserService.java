/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

import com.posabro.ocsys.security.controller.UserController;
import com.posabro.ocsys.security.domain.Page;
import com.posabro.ocsys.security.domain.PageQuery;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.repository.UserRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos
 */
@Service("userService")
@Repository
@Transactional
public class DefaultUserService implements UserService {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Page queryUsersPage(PageQuery pageQuery) {
        logger.debug("queryUsersPage init");
        StringBuilder queryBuilder = new StringBuilder("select u from User u where name like ?1 ");
        if (pageQuery.getSortOrder() != null) {
            queryBuilder.append("order by u.").append(pageQuery.getSortOrder().getColumn()).append(" ").append(pageQuery.getSortOrder().getType());
        }
        Query query = em.createQuery(queryBuilder.toString());
        query.setParameter(1, "%" + pageQuery.getSearchPattern() + "%");
        query.setFirstResult(pageQuery.getStartIndex());
        List<User> allUsers = query.getResultList();
        int totalRows = pageQuery.getStartIndex() + allUsers.size();
        List<User> users;
        if (pageQuery.getSize() < allUsers.size()) {
            users = allUsers.subList(0, pageQuery.getSize());
        } else {
            users = allUsers;
        }
        Page page = new Page(users, String.valueOf(pageQuery.getEcho()), totalRows);
        logger.debug("queryUsersPage : " + page);
        return page;
    }

    @Override
    public void saveUser(User user) {
        user.setCreationDate(new Date());
        logger.debug("saveUser " + user);
        user.setPassword(hashParameter(user.getPassword()));
        if (!userRepository.exists(user.getName())) {
            userRepository.save(user);
        } else {
            throw new JpaSystemException(new PersistenceException("user " + user.getName() + " already exists"));
        }
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(hashParameter(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void removeUser(String name) {
        userRepository.delete(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(String name) {
        return userRepository.findOne(name);
    }

    private char[] hashParameter(char[] password) {

        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(new String(password), null);
        return hashedPass.toCharArray();

//        
//        char[] passEncoded = null;
//        try {
//            ByteBuffer passBuffer = Charset.defaultCharset().encode(CharBuffer.wrap(password));
//            byte[] passBytes = passBuffer.array();
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.update(passBytes, 0, password.length);
//            passEncoded = new BigInteger(1, messageDigest.digest()).toString(16).toCharArray();
//        } catch (NoSuchAlgorithmException ex) {
//            logger.error("Error while encrypting a password", ex);
//        }
//        return passEncoded;
    }
}
