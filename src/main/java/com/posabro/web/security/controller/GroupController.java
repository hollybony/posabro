/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.controller;

import com.posabro.web.commons.JQueryPage;
import com.posabro.web.commons.Misc;
import com.posabro.web.commons.PageRequestBuilder;
import com.posabro.web.commons.ReportSpec;
import com.posabro.web.controller.ValidationController;
import com.posabro.web.excel.ReportExcelView;
import com.posabro.web.security.domain.Group;
import com.posabro.web.security.services.GroupService;
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
 * Web controller that processes all the requests related to groups
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/groupController/*")
public class GroupController extends ValidationController{
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(GroupController.class);
    
    /**
     * The groupService
     */
    @Autowired
    private GroupService groupService;
    
    /**
     * Searches for all the groups by using a <code>Pageable</code> object which is created based on the
     * request parameter.
     * 
     * @param request - the request which will be used to populate the <code>Pageable</code> object
     * @return the <code>JQueryPage</code> which contains the groups found.
     */
    @RequestMapping("filter")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody JQueryPage filter(HttpServletRequest request) {
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
    
    /**
     * Stores the <code>Group</code> received as parameter
     * 
     * @param group - the group to store
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "store", method = RequestMethod.POST)
    public void store(@Valid @RequestBody Group group, HttpServletResponse response) {
        logger.debug("storeGroup init");
        groupService.saveGroup(group);
    }
    
    /**
     * Updates the <code>Group</code> received as parameter
     * 
     * @param group - the group to update
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@Valid @RequestBody Group group, HttpServletResponse response) {
        groupService.updateGroup(group);
    }
    
    /**
     * Deletes the <code>Group</code> with the id received as parameter
     * 
     * @param id - the Id of the group to delete
     * @param response - it is not used but required by Spring mapping
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody long id, HttpServletResponse response) {
        logger.debug("deleteGroup init : " + id);
        groupService.removeGroup(id);
    }
    
    /**
     * Finds the <code>Group</code> which has the id received as parameter
     * 
     * @param id - the group Id to find
     * @return the group found
     */
    @RequestMapping(value = "findById")
    public @ResponseBody Group findById(@RequestBody Long id) {
        return groupService.findGroup(id);
    }
    
    /**
     * Gets all the groups
     * 
     * @return the groups found
     */
    @RequestMapping("getAll")
    public @ResponseBody
    List<Group> getAll() {
        return groupService.getAllGroups();
    }
    
    /**
     * Founds and exports all the groups, the exporting format is specified by the output path variable
     * 
     * @param output - must be either excel or pdf
     * @return <code>ModelAndView</code> object that leads to the suitable view of the format selected
     */
    @RequestMapping(value = "export/{output}", method= RequestMethod.GET)
    public ModelAndView export(@PathVariable String output){
        List<Group> allGroups = groupService.getAllGroups();
        ReportSpec<Group> reportSpec = new ReportSpec<Group>(allGroups);
        reportSpec.setI18nTitle("groups.report.title");
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
