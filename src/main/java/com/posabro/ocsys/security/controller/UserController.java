/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

import com.posabro.ocsys.security.domain.Misc;
import com.posabro.ocsys.security.domain.PageRequestBuilder;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.services.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/userController/*")
public class UserController extends ValidationController{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("filter")
    @PreAuthorize("hasRole('ROLE_ADMIN') and fullyAuthenticated")
    public @ResponseBody
    com.posabro.ocsys.security.domain.JQueryPage filterUsers(HttpServletRequest request) {
        Pageable pageable = PageRequestBuilder.build(request);
        String echo = request.getParameter("sEcho");
        String searchPattern = request.getParameter("sSearch");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Page<User> page;
        try {
            Date creationDate = dateFormat.parse(searchPattern);
            page = userService.queryPageByCreationDate(creationDate, pageable);
        } catch (ParseException ex) {
            page = userService.queryPageByName(searchPattern, pageable);
        }
        return Misc.pageToJQueryPage(page, echo);
    }

    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void storeUser(@Valid @RequestBody User user, HttpServletResponse response) {
        logger.debug("storeUser init");
        userService.saveUser(user);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user, HttpServletResponse response) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteUser(@RequestBody String name, HttpServletResponse response) {
        logger.debug("deleteUser init : " + name);
        userService.removeUser(name);
    }

    @RequestMapping(value = "findById")
    public @ResponseBody
    User findUserById(@RequestBody String name) {
        return userService.findUser(name);
    }
}
