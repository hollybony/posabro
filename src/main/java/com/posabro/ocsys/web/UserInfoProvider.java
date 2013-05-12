/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.User;
import com.posabro.ocsys.services.BranchService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Carlos Juarez
 */
public class UserInfoProvider implements ApplicationContextAware{

    private static ApplicationContext ctx = null;
    
    private static User user = null;
    
    public static User getLoggedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUser;
//        if (authentication != null) {
//            currentUser = authentication.getName();
//        } else {
//            currentUser = "anonymous";
//        }
//        return currentUser;
        if(user==null){
            BranchService branchService = (BranchService) ctx.getBean("branchService");
            Branch branch = branchService.getAllBranches().get(0);
            user = new User("anonymous",branch);
        }
        return user;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
    
    
}
