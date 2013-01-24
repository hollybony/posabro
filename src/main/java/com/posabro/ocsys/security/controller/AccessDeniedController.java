/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.controller;

import com.posabro.ocsys.security.domain.Page;
import com.posabro.ocsys.security.domain.PageQuery;
import com.posabro.ocsys.security.domain.QueryReportBuilder;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos
 */
@Controller
@RequestMapping("/accessDenied/*")
public class AccessDeniedController {

    @RequestMapping(value="acd", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    void queryUsers(HttpServletRequest request) {
        javax.swing.JOptionPane.showMessageDialog(null, "ya chigue");
    }
}
