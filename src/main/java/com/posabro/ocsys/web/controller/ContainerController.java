/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.services.ContainerService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/facilityController/*")
public class ContainerController {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(ContainerController.class);
    
    @Autowired
    private ContainerService containerService;
    
    @RequestMapping(value="findContainer", method = RequestMethod.POST)
    public Container findContainer(@RequestBody String containerId){
        Container foundContainer = containerService.findContainer(containerId);
        return foundContainer;
    }
    
}
