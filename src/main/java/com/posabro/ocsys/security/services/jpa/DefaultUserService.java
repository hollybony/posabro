/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.security.controller.UserController;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.repository.UserRepository;
import com.posabro.ocsys.security.services.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Page<User> queryPageByName(String name, Pageable pageable) {
        return userRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<User> queryPageByCreationDate(Date creationDate, Pageable pageable) {
        return userRepository.findByCreationDate(creationDate, pageable);
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
    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
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
    }
}
