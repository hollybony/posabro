/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

import com.posabro.ocsys.commons.JQueryPage;
import com.posabro.ocsys.commons.Misc;
import com.posabro.ocsys.commons.PageRequestBuilder;
import com.posabro.ocsys.commons.ReportSpec;
import com.posabro.ocsys.excel.ReportExcelView;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.services.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody JQueryPage filterUsers(HttpServletRequest request) {
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

    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void storeUser(@Valid @RequestBody User user, HttpServletResponse response) {
        logger.debug("storeUser init");
        userService.saveUser(user);
//        TODO it makes the user log out so it is important to warn that this will happen
//        SecurityContextHolder.clearContext();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateUser(@Valid @RequestBody User user, HttpServletResponse response) {
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
    
    @RequestMapping("getAll")
    public @ResponseBody
    List<User> getAll() {
        return userService.getAllUsers();
    }
    
    @RequestMapping(value = "export/{output}", method= RequestMethod.GET)
    public ModelAndView export(@PathVariable String output){
        List<User> allUsers = userService.getAllUsers();
        ReportSpec<User> reportSpec = new ReportSpec<User>(allUsers);
        reportSpec.setTitleKey("users.report.title");
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
}
