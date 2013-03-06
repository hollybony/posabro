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
import com.posabro.ocsys.security.domain.Group;
import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.services.GroupService;
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
@RequestMapping("/groupController/*")
public class GroupController extends ValidationController{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(GroupController.class);
    
    @Autowired
    private GroupService groupService;
    
    @RequestMapping("filter")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody JQueryPage filterGroups(HttpServletRequest request) {
        Pageable pageable = PageRequestBuilder.build(request);
        String echo = request.getParameter("sEcho");
        String searchPattern = request.getParameter("sSearch");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Page<Group> page;
        try {
            Date datePattern = dateFormat.parse(searchPattern);
            page = groupService.queryPageByDatePattern(datePattern, pageable);
        } catch (ParseException ex) {
            page = groupService.queryPageByStringPattern(searchPattern, pageable);
        }
        return Misc.pageToJQueryPage(page, echo);
    }
    
    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void storeGroup(@Valid @RequestBody Group group, HttpServletResponse response) {
        logger.debug("storeGroup init");
        groupService.saveGroup(group);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updateGroup(@Valid @RequestBody Group group, HttpServletResponse response) {
        groupService.updateGroup(group);
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deleteGroup(@RequestBody long id, HttpServletResponse response) {
        logger.debug("deleteGroup init : " + id);
        groupService.removeGroup(id);
    }
    
    @RequestMapping(value = "findById")
    public @ResponseBody
    Group findGroupById(@RequestBody Long id) {
        return groupService.findGroup(id);
    }
    
    @RequestMapping("getAll")
    public @ResponseBody
    List<Group> getAll() {
        return groupService.getAllGroups();
    }
    
    @RequestMapping(value = "export/{output}", method= RequestMethod.GET)
    public ModelAndView export(@PathVariable String output){
        List<Group> allGroups = groupService.getAllGroups();
        ReportSpec<Group> reportSpec = new ReportSpec<Group>(allGroups);
        reportSpec.setTitleKey("groups.report.title");
        reportSpec.addColumn(new ReportSpec.Column("group.id","id")).addColumn(new ReportSpec.Column("group.name","name")).
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
