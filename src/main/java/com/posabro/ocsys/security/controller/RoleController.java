/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.services.RoleService;
import java.util.Arrays;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/roleController/*")
public class RoleController extends ValidationController{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void storeRole(@Valid @RequestBody Role role, HttpServletResponse response) {
        logger.debug("storeRole init");
        throw new JpaSystemException(new PersistenceException("Not available"));
    }

    @RequestMapping("getAll")
    public @ResponseBody
    List<Role> getAll() {
        return roleService.getAllRoles();
    }

}
