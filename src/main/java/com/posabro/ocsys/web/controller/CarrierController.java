/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.Carrier;
import com.posabro.ocsys.services.CarrierService;
import com.posabro.web.controller.ValidationController;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web controller that processes all the requests related to carriers
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/carrierController/*")
public class CarrierController extends ValidationController{
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(CarrierController.class);
    
    /**
     * The carrierService
     */
    @Autowired
    private CarrierService carrierService;
    
    /**
     * Gets all the carriers
     * 
     * @return the carriers found
     */
    @RequestMapping(value="getAllCarriers", method = RequestMethod.POST)
    public @ResponseBody List<Carrier> getAllCarriers(){
        List<Carrier> allCarriers = carrierService.getAllCarriers();
        return allCarriers;
    }
    
}
