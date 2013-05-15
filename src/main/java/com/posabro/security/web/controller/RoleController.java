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
import com.posabro.security.domain.Role;
import com.posabro.security.services.RoleService;
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
 * Web controller that processes all the request related to roles
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/roleController/*")
public class RoleController extends ValidationController{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    /**
     * The roleService
     */
    @Autowired
    private RoleService roleService;
    
    /**
     * Searches for all the roles by using a <code>Pageable</code> object which is created based on the
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
        Page<Role> page;
        try {
            Date datePattern = dateFormat.parse(searchPattern);
            page = roleService.queryPageByDatePattern(datePattern, pageable);
        } catch (ParseException ex) {
            page = roleService.queryPageByStringPattern(searchPattern, pageable);
        }
        return Misc.pageToJQueryPage(page, echo);
    }

    /**
     * Stores the <code>Role</code> received as parameter
     * 
     * @param role - the role to store
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void store(@Valid @RequestBody Role role, HttpServletResponse response) {
        logger.debug("storeRole init");
        roleService.saveRole(role);
    }
    
    /**
     * Updates the <code>Role</code> received as parameter
     * 
     * @param role - the role to update
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@Valid @RequestBody Role role, HttpServletResponse response) {
        roleService.updateRole(role);
    }
    
    /**
     * Deletes the <code>Role</code> with the name received as parameter
     * 
     * @param name - the name of the role to delete
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody String name, HttpServletResponse response) {
        logger.debug("deleteUser init : " + name);
        roleService.removeRole(name);
    }
    
    /**
     * Finds the <code>Role</code> which the name received as parameter
     * 
     * @param name - the role name to found
     * @return the role found
     */
    @RequestMapping(value = "findById")
    public @ResponseBody Role findById(@RequestBody String name) {
        return roleService.findRole(name);
    }

    /**
     * Gets all the roles
     * 
     * @return the roles found
     */
    @RequestMapping("getAll")
    public @ResponseBody
    List<Role> getAll() {
        return roleService.getAllRoles();
    }
    
    /**
     * Founds and exports all the roles, the exporting format is specified by the output path variable
     * 
     * @param output - must be either excel or pdf
     * @return <code>ModelAndView</code> object that leads to the suitable view of the format selected
     */
    @RequestMapping(value = "export/{output}", method= RequestMethod.GET)
    public ModelAndView export(@PathVariable String output){
        List<Role> allRoles = roleService.getAllRoles();
        ReportSpec<Role> reportSpec = new ReportSpec<Role>(allRoles);
        reportSpec.setI18nTitle("roles.report.title");
        reportSpec.addColumn(new ReportSpec.Column("role.name","name")).addColumn(new ReportSpec.Column("role.description","description")).
                addColumn(new ReportSpec.Column("auditor.createdDate","auditData.createdDate"));
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
