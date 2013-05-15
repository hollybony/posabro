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
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/carrierController/*")
public class CarrierController extends ValidationController{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(CarrierController.class);
    
    @Autowired
    private CarrierService carrierService;
    
    @RequestMapping(value="getAllCarriers", method = RequestMethod.POST)
    public @ResponseBody List<Carrier> getAllCarriers(){
        List<Carrier> allCarriers = carrierService.getAllCarriers();
        return allCarriers;
    }
    
}
