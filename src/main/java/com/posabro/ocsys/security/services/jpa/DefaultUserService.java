/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.security.controller.UserController;
import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.repository.UserRepository;
import com.posabro.ocsys.security.services.GroupService;
import com.posabro.ocsys.security.services.UserService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
    public void saveUser(User user) {
        logger.debug("about to save : " + user);
        user.setPassword(hashParameter(user.getPassword()));
        if (!userRepository.exists(user.getName())) {
            userRepository.save(user);
        } else {
            throw new JpaSystemException(new PersistenceException("user " + user.getName() + " already exists"));
        }
    }

    @Override
    public void updateUser(User user) {
        logger.debug("about to update : " + user);
        user.setPassword(hashParameter(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void removeUser(String name) {
        if(groupService.isGivenMemberNameBeingUsed(name)){
            throw new JpaSystemException(new PersistenceException("user " + name + " is used by some groups"));
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
    @Transactional(readOnly = true)
    public boolean isGivenRoleNameBeingUsed(String name){
        List<User> usersFound = userRepository.findByRoleName(name);
        logger.debug("usersFound : " + usersFound);
        return !usersFound.isEmpty();
    }
    
    private char[] hashParameter(char[] password) {
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(new String(password), null);
        return hashedPass.toCharArray();
    }

}
