/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.web.controller;

import com.posabro.web.commons.JQueryPage;
import com.posabro.web.commons.Misc;
import com.posabro.web.commons.PageRequestBuilder;
import com.posabro.web.commons.ReportSpec;
import com.posabro.web.controller.ValidationController;
import com.posabro.web.excel.ReportExcelView;
import com.posabro.security.domain.User;
import com.posabro.security.services.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web controller that processes all the request related to users
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/userController/*")
public class UserController extends ValidationController{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /**
     * The userService
     */
    @Autowired
    private UserService userService;

    /**
     * Searches for all the users by using a <code>Pageable</code> object which is created based on the
     * request parameter
     * 
     * @param request - the request which will be used to populate the <code>Pageable</code> object
     * @return the <code>JQueryPage</code> which contains the roles found
     */
    @RequestMapping("filter")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody JQueryPage filter(HttpServletRequest request) {
        Pageable pageable = PageRequestBuilder.build(request);
        String echo = request.getParameter("sEcho");
        String searchPattern = request.getParameter("sSearch");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Page<User> page;
        try {
            Date datePattern = dateFormat.parse(searchPattern);
            page = userService.queryPageByDatePattern(datePattern, pageable);
        } catch (ParseException ex) {
            page = userService.queryPageByStringPattern(searchPattern, pageable);
        }
        return Misc.pageToJQueryPage(page, echo);
    }

    /**
     * Stores the <code>User</code> received as parameter
     * 
     * @param user - the user to store
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "store", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void store(@Valid @RequestBody User user, HttpServletResponse response) {
        logger.debug("storeUser init");
        userService.registerUser(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName().equals(user.getName())) {
            SecurityContextHolder.clearContext();
        }
    }

    /**
     * Updates the <code>User</code> received as parameter
     * 
     * @param user - the user to update
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@Valid @RequestBody User user, HttpServletResponse response) {
        userService.updateUser(user);
    }

    /**
     * Deletes the <code>User</code> with the name received as parameter
     * 
     * @param name - the name of the role to delete
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody String name, HttpServletResponse response) {
        logger.debug("deleteUser init : " + name);
        userService.removeUser(name);
    }

    /**
     * Finds the <code>User</code> which the name received as parameter
     * 
     * @param name - the user name to found
     * @return the user found
     */
    @RequestMapping(value = "findById")
    public @ResponseBody User findById(@RequestBody String name) {
        return userService.findUser(name);
    }
    
    /**
     * Gets all the users
     * 
     * @return the users found
     */
    @RequestMapping("getAll")
    public @ResponseBody
    List<User> getAll() {
        return userService.getAllUsers();
    }
    
    /**
     * Allows to verify an email address. Every time a user's email address is updated a token is generated and
     * an email is sent to this new email address. This email contains a URL generated dynamically which must me
     * used to call this web method. The URL  must meet the following format:
     * 
     * {applicationContext}/confirmEmailAddress/{userName}/{token}
     * 
     * where userName is the login name of the user whose email address will be verified
     * and token generated when the email address was updated
     * Example URL:
     * {applicationContext}/confirmEmailAddress/admin/5dff3f36d4a7751aecc92686d000530b
     * 
     * @param userName - the userName whose email address is being verified
     * @param token - the dynamically generated token
     * 
     * @return <code>ModelAndView</code> objects which renders a suitable view that displays the result of this verification
     */
    @RequestMapping(value = "confirmEmailAddress/{userName}/{token}", method= RequestMethod.GET)
    public ModelAndView verifyEmailAddress(@PathVariable String userName, @PathVariable String token){
        ModelAndView mav = new ModelAndView("emailVerified");
        if(userName!=null && token!=null){
            User user = userService.verifyEmailAddress(token, userName);
            mav.addObject("userVerified", user);
        }
        return mav;
    }
    
    /**
     * Founds and exports all the users, the exporting format is specified by the output path variable
     * 
     * @param output - must be either excel or pdf
     * @return <code>ModelAndView</code> object that leads to the suitable view of the format selected
     */
    @RequestMapping(value = "export/{output}", method= RequestMethod.GET)
    public ModelAndView export(@PathVariable String output){
        List<User> allUsers = userService.getAllUsers();
        ReportSpec<User> reportSpec = new ReportSpec<User>(allUsers);
        reportSpec.setI18nTitle("users.report.title");
        reportSpec.addColumn(new ReportSpec.Column("user.name","name")).addColumn(new ReportSpec.Column("user.password","password")).
                addColumn(new ReportSpec.Column("user.creationDate","auditData.createdDate"));
        ModelAndView mav = new ModelAndView();
        if(output.equals("excel")){
            mav.setViewName("reportExcelView");
        }else if(output.equals("pdf")){
            mav.setViewName("reportPdfView");
        }
        mav.addObject(ReportExcelView.REPORT_SPEC, reportSpec);
        return mav;
    }
    
    /**
     * Registers the user parameter as guest. I means that the user will no be included in any group
     * but will have a default role
     * 
     * @param user - the user to register
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("not isAuthenticated()")
    public void registerGuest(@Valid @RequestBody User user, HttpServletResponse response) {
        logger.debug("registerGuest init");
        userService.registerGuest(user);
    }
    
    /**
     * Generates a temporary password and send it to the user
     * 
     * @param params - where the user name and email address will be pulled to send the temp password
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value="getTempPassword", method= RequestMethod.POST)
    @PreAuthorize("not isAuthenticated()")
    public void getTemPassword(@RequestBody Map<String,String> params, HttpServletResponse response){
        String userName = params.get("userName");
        String email = params.get("email");
        logger.debug("generate temp password for " + userName +" email address " + email);
        userService.generateTempPassword(userName, email);
    }
    
    /**
     * After a temporary password is generated and an email is sent to the user this web method must be called
     * in order to the user retrieves his account. The email contains an dynamically generated URL that must
     * meet the following format:
     * {applicationContext}/userController/verifyTempPasswordToken/{userName}/{token}
     * where userName is the user who is retrieving his account
     * and token is the dynamically generated token
     * Example URL:
     * {applicationContext}/userController/verifyTempPasswordToken/admin/5dff3f36d4a7751aecc92686d000530b
     * 
     * @param userName - the user retrieving his account
     * @param token -  the dynamically generated token
     * @return <code>ModelAndView</code> object that leads to a view which will allow to create a new password
     */
    @RequestMapping(value = "verifyTempPasswordToken/{userName}/{token}", method= RequestMethod.GET)
    public ModelAndView verifyTempPasswordToken(@PathVariable String userName, @PathVariable String token){
        boolean verifyTempPassword = userService.isVerifiedTempPassword(userName, token);
        ModelAndView mav = new ModelAndView("generateNewPassword");
        mav.addObject("userName", userName);
        mav.addObject("token", token);
        mav.addObject("verifyTempPassword", verifyTempPassword);
        return mav;
    }
    
    /**
     * Allows to create a new password after the user generated a temporary one
     * 
     * @param params which contains the userName, tempPassword and newPassword
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value="getNewPassword", method= RequestMethod.POST)
    @PreAuthorize("not isAuthenticated()")
    public void getNewPassword(@RequestBody Map<String,String> params, HttpServletResponse response){
        String userName = params.get("userName");
        String tempPassword = params.get("tempPassword");
        String newPassword = params.get("newPassword");
        String token = params.get("token");
        logger.debug("retrieving password for userName {0} and token {1}", new Object[]{userName, token});
        userService.retrievePassword(userName,tempPassword.toCharArray(), newPassword.toCharArray(),token);
    }
    
}
