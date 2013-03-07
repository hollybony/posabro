/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Carlos Juarez
 */
public interface RoleService {
    
    public Page<Role> queryPageByStringPattern(String pattern, Pageable pageable);
    
    public Page<Role> queryPageByDatePattern(Date datePattern, Pageable pageable);
    
    public void saveRole(Role role);
    
    public void updateRole(Role role);

    public List<Role> getAllRoles();

    public void removeRole(String name);

    public Role findRole(String name);
    
    public Role getDefaultRole();
    
}
