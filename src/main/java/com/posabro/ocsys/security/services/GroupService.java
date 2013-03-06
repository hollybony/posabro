/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

import com.posabro.ocsys.security.domain.Group;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Carlos Juarez
 */
public interface GroupService {
    
    public Page<Group> queryPageByStringPattern(String pattern, Pageable pageable);
    
    public Page<Group> queryPageByDatePattern(Date datePattern, Pageable pageable);
    
    public void saveGroup(Group group);
    
    public void updateGroup(Group group);

    public List<Group> getAllGroups();

    public void removeGroup(Long id);

    public Group findGroup(Long id);
    
    public boolean isGivenRoleNameBeingUsed(String name);
    
    public boolean isGivenMemberNameBeingUsed(String name);
    
}
