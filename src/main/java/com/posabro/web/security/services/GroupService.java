/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.services;

import com.posabro.web.security.domain.Group;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Contains all the service methods of <code>Group</code>s
 * 
 * @author Carlos Juarez
 */
public interface GroupService {
    
    /**
     * Queries the groups that match with the string pattern in the properties that apply as well as the page
     * constraints
     * 
     * @param pattern - the string pattern
     * @param pageable - the page constraints to apply
     * @return the page generated
     */
    public Page<Group> queryPageByStringPattern(String pattern, Pageable pageable);
    
    /**
     * Queries the groups that match with the date pattern in the properties that apply as well as the page
     * constraints
     * 
     * @param datePattern - the date pattern
     * @param pageable - the page constraints to apply
     * @return the page generated
     */
    public Page<Group> queryPageByDatePattern(Date datePattern, Pageable pageable);
    
    /**
     * Saves a group
     * 
     * @param group - the group to save
     */
    public void saveGroup(Group group);
    
    /**
     * Updates a group
     * 
     * @param group - the group to save
     */
    public void updateGroup(Group group);

    /**
     * Gets all the groups
     * 
     * @return groups found
     */
    public List<Group> getAllGroups();

    /**
     * Removes a group
     * 
     * @param id - the group with this id is going to be removed
     */
    public void removeGroup(Long id);

    /**
     * Finds a group by id
     * 
     * @param id - the group with this is id is going to be found
     * @return  the group found
     */
    public Group findGroup(Long id);
    
    /**
     * Verifies if the role with the name specified is contained by any group
     * 
     * @param name the role name to be verified
     * @return <code>true</code> if the role is being used otherwise <code>false</code>
     */
    public boolean isGivenRoleNameBeingUsed(String name);
    
    /**
     * Verifies if the user with the name specified is member of any group
     * 
     * @param name the user name to verified
     * @return <code>true</code> if the user is being used otherwise <code>false</code>
     */
    public boolean isGivenMemberNameBeingUsed(String name);
    
}
