/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.services.FacilityService;
import com.posabro.ocsys.web.UserInfoProvider;
import com.posabro.web.controller.ValidationController;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/facilityController/*")
public class FacilityController extends ValidationController{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(FacilityController.class);
    
    @Autowired
    private FacilityService facilityService;
    
    @RequestMapping(value="findFacilitiesByCustomer", method = RequestMethod.POST)
    public @ResponseBody List<Facility> findFacilityByCustomer(@RequestBody String customerId){
        String companyId = UserInfoProvider.getLoggedUser().getBranch().getCompany().getId();
        CustomerPK customerPK = new CustomerPK(customerId, companyId);
        return facilityService.findFacilitiesByCustomer(customerPK);
    }
}
