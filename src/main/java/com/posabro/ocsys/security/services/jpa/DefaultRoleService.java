/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.repository.RoleRepository;
import com.posabro.ocsys.security.services.GroupService;
import com.posabro.ocsys.security.services.RoleService;
import com.posabro.ocsys.security.services.UserService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Juarez
 */
@Service("roleService")
@Repository
@Transactional
public class DefaultRoleService implements RoleService {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultRoleService.class);
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private GroupService groupService;

    @Override
    @Transactional(readOnly = true)
    public Page<Role> queryPageByStringPattern(String pattern, Pageable pageable) {
        return roleRepository.findByNameContainingOrDescriptionContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(pattern, pattern, pattern, pattern, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Role> queryPageByDatePattern(Date datePattern, Pageable pageable) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datePattern);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);       
        Date upToDate = calendar.getTime();
        return roleRepository.findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(datePattern, upToDate, datePattern, upToDate, pageable);
    }

    @Override
    public void saveRole(Role role) {
        logger.debug("about to save : " + role);
        role.setName(role.getName().toUpperCase());
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
        if (!roleRepository.exists(role.getName())) {
            roleRepository.save(role);
        } else {
            throw new JpaSystemException(new PersistenceException("role " + role.getName() + " already exists"));
        }
    }

    @Override
    public void updateRole(Role role) {
        logger.debug("about to update : " + role);
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Role> getAllRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }
    
    @Override
    public void removeRole(String name) {
        logger.debug("about to delete role " + name);
        if(userService.isGivenRoleNameBeingUsed(name)){
            throw new JpaSystemException(new PersistenceException("role " + name + " is used by some users"));
        }else if(groupService.isGivenRoleNameBeingUsed(name)){
            throw new JpaSystemException(new PersistenceException("role " + name + " is used by some groups"));
        }else{
            roleRepository.delete(name);
        }
    }

    @Override
    public Role findRole(String name) {
        return roleRepository.findOne(name);
    }

}
