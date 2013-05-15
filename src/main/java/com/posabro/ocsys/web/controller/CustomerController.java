/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.services.CustomerService;
import com.posabro.ocsys.web.UserInfoProvider;
import com.posabro.web.controller.ValidationController;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/customerController/*")
public class CustomerController extends ValidationController{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping(value="findCustomers", method = RequestMethod.POST)
    public @ResponseBody List<Customer> findCustomers(){
        String companyId = UserInfoProvider.getLoggedUser().getBranch().getCompany().getId();
        List<Customer> customersByCompany = customerService.findCustomersByCompany(companyId);
        return customersByCompany;
    }
    
}
