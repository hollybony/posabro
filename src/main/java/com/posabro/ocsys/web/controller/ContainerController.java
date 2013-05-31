/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.services.ContainerService;
import com.posabro.web.controller.ValidationController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web controller that processes all the requests related to containers
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/containerController/*")
public class ContainerController extends ValidationController{
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(ContainerController.class);
    
    /**
     * The containerService
     */
    @Autowired
    private ContainerService containerService;
    
    /**
     * Finds the container that has the id given
     * 
     * @param containerId - the containerId with which the container is looked for
     * @return the container found
     */
    @RequestMapping(value="findContainer", method = RequestMethod.POST)
    public @ResponseBody Container findContainer(@RequestBody String containerId){
        logger.debug("finding container : " + containerId);
        Container foundContainer = containerService.findContainer(containerId);
        return foundContainer;
    }
    
}
