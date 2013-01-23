/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.services.RoleService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos
 */
@Controller
@RequestMapping("/roleController/*")
public class RoleController {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("getAll")
    public @ResponseBody Iterable<Role> getAll(){
        return roleService.getAllRoles();
    }
    
}
