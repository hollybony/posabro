/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

import com.posabro.ocsys.security.domain.ErrorMessage;
import com.posabro.ocsys.security.domain.Page;
import com.posabro.ocsys.security.domain.PageQuery;
import com.posabro.ocsys.security.domain.QueryReportBuilder;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.services.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Carlos
 */
@Controller
@RequestMapping("/userController/*")
public class UserController {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("queryUsers")
    public @ResponseBody
    Page queryUsers(HttpServletRequest request) {
        logger.debug("queryUsers init");
        PageQuery pageQuery = QueryReportBuilder.build(request);
        Page page = userService.queryUsersPage(pageQuery);
        logger.debug("queryUsers end page : " + page);
        return page;
    }

    @RequestMapping(value = "store", method = RequestMethod.POST, consumes = "application/json")
    public void storeUser(@RequestBody User user, HttpServletResponse response) throws IOException {
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
    User findUser(@RequestBody String name) {
        return userService.findUser(name);
    }

    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "JpaSystemException", method = {RequestMethod.POST, RequestMethod.POST})
    public @ResponseBody
    ErrorMessage handleException(JpaSystemException ex, HttpServletResponse response) {
        logger.debug("handleException init");
        return new ErrorMessage(ex.getMessage());
    }
}
