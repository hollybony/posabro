/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.repository.RoleRepository;
import com.posabro.ocsys.security.services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    @Transactional(readOnly=true)
    public List<Role> getAllRoles() {
        return Lists.newArrayList(roleRepository.findAll());
    }

}
