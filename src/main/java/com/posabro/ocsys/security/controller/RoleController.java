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
import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.services.RoleService;
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
@RequestMapping("/roleController/*")
public class RoleController extends ValidationController{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("filter")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody JQueryPage filterRoles(HttpServletRequest request) {
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

    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void storeRole(@Valid @RequestBody Role role, HttpServletResponse response) {
        logger.debug("storeRole init");
        roleService.saveRole(role);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateRole(@Valid @RequestBody Role role, HttpServletResponse response) {
        roleService.updateRole(role);
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteRole(@RequestBody String name, HttpServletResponse response) {
        logger.debug("deleteUser init : " + name);
        roleService.removeRole(name);
    }
    
    @RequestMapping(value = "findById")
    public @ResponseBody
    Role findRoleById(@RequestBody String name) {
        return roleService.findRole(name);
    }

    @RequestMapping("getAll")
    public @ResponseBody
    List<Role> getAll() {
        return roleService.getAllRoles();
    }
    
    @RequestMapping(value = "export/{output}", method= RequestMethod.GET)
    public ModelAndView export(@PathVariable String output){
        List<Role> allRoles = roleService.getAllRoles();
        ReportSpec<Role> reportSpec = new ReportSpec<Role>(allRoles);
        reportSpec.setTitleKey("roles.report.title");
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
