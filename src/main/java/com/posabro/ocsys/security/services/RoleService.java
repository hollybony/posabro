/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

import com.posabro.ocsys.security.domain.Role;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface RoleService {
    
    public List<Role> getAllRoles();
    
}
