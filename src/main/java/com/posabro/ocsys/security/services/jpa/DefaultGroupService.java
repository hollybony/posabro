/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.security.domain.Group;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.repository.GroupRepository;
import com.posabro.ocsys.security.services.GroupService;
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
@Service("groupService")
@Repository
@Transactional
public class DefaultGroupService implements GroupService{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultGroupService.class);
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Page<Group> queryPageByStringPattern(String pattern, Pageable pageable) {
        Long id;
        try{
            id = Long.parseLong(pattern);
        }catch(NumberFormatException ex){
            id = null;
        }
        return groupRepository.findByIdOrNameContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(id, pattern, pattern, pattern, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Group> queryPageByDatePattern(Date datePattern, Pageable pageable) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datePattern);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);       
        Date upToDate = calendar.getTime();
        return groupRepository.findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(datePattern, upToDate, datePattern, upToDate, pageable);
    }

    @Override
    public void saveGroup(Group group) {
        logger.debug("about to save : " + group);
        if (!groupRepository.exists(group.getId())) {
            groupRepository.save(group);
        } else {
            throw new JpaSystemException(new PersistenceException("group " + group.getId() + " already exists"));
        }
    }

    @Override
    public void updateGroup(Group group) {
        logger.debug("about to update : " + group);
        groupRepository.save(group);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Group> getAllGroups() {
        return Lists.newArrayList(groupRepository.findAll());
    }

    @Override
    public void removeGroup(Long id) {
        groupRepository.delete(id);
    }

    @Override
    public Group findGroup(Long id) {
        return groupRepository.findOne(id);
    }
    
    @Override
    public boolean isGivenRoleNameBeingUsed(String name){
        List<Group> groupsFound = groupRepository.findByRoleName(name);
        logger.debug("groupsFound : " + groupsFound);
        return !groupsFound.isEmpty();
    }

    @Override
    public boolean isGivenMemberNameBeingUsed(String name) {
        List<User> usersFound = groupRepository.findByMemberName(name);
        logger.debug("usersFound : " + usersFound);
        return !usersFound.isEmpty();
    }
    
}
