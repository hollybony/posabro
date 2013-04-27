/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.web.security.domain.Role;
import com.posabro.web.security.repository.RoleRepository;
import com.posabro.web.security.repository.UserRepository;
import com.posabro.web.security.services.GroupService;
import com.posabro.web.security.services.RoleService;
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
 * Default implementation of <code>RoleService</code>
 * 
 * @author Carlos Juarez
 */
@Service("roleService")
@Repository
@Transactional
public class DefaultRoleService implements RoleService {
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultRoleService.class);
    
    /**
     * The roleRepository
     */
    @Autowired
    private RoleRepository roleRepository;
    
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
     * @see RoleService#queryPageByStringPattern(java.lang.String, org.springframework.data.domain.Pageable) 
     * 
     * @param pattern
     * @param pageable
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Role> queryPageByStringPattern(String pattern, Pageable pageable) {
        return roleRepository.findByNameContainingOrDescriptionContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(pattern, pattern, pattern, pattern, pageable);
    }

    /**
     * @see RoleService#queryPageByDatePattern(java.util.Date, org.springframework.data.domain.Pageable) 
     * 
     * @param datePattern
     * @param pageable
     * @return 
     */
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

    /**
     * @see RoleService#saveRole(com.posabro.web.security.domain.Role) 
     * If the role already exists a <code>JpaSystemException</code> is thrown
     * 
     * @param role 
     */
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

    /**
     * @see RoleService#updateRole(com.posabro.web.security.domain.Role) 
     * 
     * @param role 
     */
    @Override
    public void updateRole(Role role) {
        logger.debug("about to update : " + role);
        roleRepository.save(role);
    }

    /**
     * @see RoleService#getAllRoles()
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Role> getAllRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }
    
    /**
     * @see RoleService#removeRole(java.lang.String)
     * 
     * @param name 
     */
    @Override
    public void removeRole(String name) {
        logger.debug("about to delete role " + name);
        if(!userRepository.findByRoleName(name).isEmpty()){
            throw new JpaSystemException(new PersistenceException("role " + name + " is used by some users"));
        }else if(groupService.isGivenRoleNameBeingUsed(name)){
            throw new JpaSystemException(new PersistenceException("role " + name + " is used by some groups"));
        }else{
            roleRepository.delete(name);
        }
    }

    /**
     * @see RoleService#findRole(java.lang.String)
     * 
     * @param name
     * @return 
     */
    @Override
    public Role findRole(String name) {
        return roleRepository.findOne(name);
    }

    /**
     * @see RoleService#getDefaultRole()
     * 
     * @return the first role found by the repository
     */
    @Override
    @Transactional(readOnly=true)
    public Role getDefaultRole() {
        return roleRepository.findAll().iterator().next();
    }

}
