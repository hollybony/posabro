/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.services;

import com.posabro.security.domain.Role;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Contains all the service methods of <code>Role</code>s
 * 
 * @author Carlos Juarez
 */
public interface RoleService {
   
    /**
     * Queries the roles that match with the string pattern in the properties that apply as well as the page
     * constraints
     * 
     * @param pattern - the string pattern
     * @param pageable - the page constraint to apply
     * @return the page generated
     */
    public Page<Role> queryPageByStringPattern(String pattern, Pageable pageable);
    
    /**
     * Queries the roles that match with the date pattern in the properties that apply as well as the page
     * constraints
     * 
     * @param datePattern - the date pattern
     * @param pageable - the page constraints to apply
     * @return the page generated
     */
    public Page<Role> queryPageByDatePattern(Date datePattern, Pageable pageable);
    
    /**
     * Saves a role
     * 
     * @param role - the role to save
     */
    public void saveRole(Role role);
    
    /**
     * Updates a role
     * 
     * @param role - the role to update
     */
    public void updateRole(Role role);

    /**
     * Gets all the roles
     * 
     * @return roles found
     */
    public List<Role> getAllRoles();

    /**
     * Removes a role
     * 
     * @param name - the name of role that is going to be removed
     */
    public void removeRole(String name);

    /**
     * Finds a role
     * 
     * @param name - name of the role that is going to be found
     * @return role found
     */
    public Role findRole(String name);
    
    /**
     * @return the default role
     */
    public Role getDefaultRole();
    
}
