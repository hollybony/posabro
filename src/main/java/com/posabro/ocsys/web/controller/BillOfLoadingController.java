/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

import com.posabro.ocsys.domain.BillOfLading;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import com.posabro.ocsys.services.OutboundBolService;
import com.posabro.ocsys.web.UserInfoProvider;
import com.posabro.web.controller.ValidationController;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web controller that processes all the request related to bill of loading
 * 
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/billOfLoadingController/*")
public class BillOfLoadingController extends ValidationController{
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(BillOfLoadingController.class);
    
    /**
     * The outboundBolService
     */
    @Autowired
    private OutboundBolService outboundBolService;
    
    /**
     * Stores the <code>OutboundBol</code> received as parameter
     * 
     * @param outboundBol - the outbound boL to store
     * @return the id of the outbound boL just stored
     */
    @RequestMapping(value="storeOutboundBol", method = RequestMethod.POST)
    public @ResponseBody String store(@Valid @RequestBody OutboundBol outboundBol){
        BranchPK branchPK = UserInfoProvider.getLoggedUser().getBranch().getBranchPK();
        logger.debug("store a new outboundBol for branch : " + branchPK);
        String bolId = outboundBolService.saveOutboundBol(branchPK, outboundBol);
        return bolId;
    }
    
    /**
     * Finds the <code>OutboundBol</code> which has the id received as parameter
     * 
     * @param outboundBolPK - the outbound boL id to find
     * @return the outbound boL found
     */
    @RequestMapping(value="findOutboundBol", method = RequestMethod.POST)
    public @ResponseBody OutboundBol findOutboundBol(@Valid @RequestBody OutboundBolPK outboundBolPK){
        OutboundBol foundOutboundBol = outboundBolService.findOutboundBol(outboundBolPK);
        return foundOutboundBol;
    }
    
    /**
     * exports to PDF the outbound boL with the id received as parameter
     * 
     * @param bolId - the id with which the outbound boL is looked for
     * @return modelAndView of the PDF view
     */
    @RequestMapping(value="outboundBolToPdf/{bolId}", method = RequestMethod.GET)
    public ModelAndView exportOutboundBol(@PathVariable String bolId){
        BranchPK branchPK = UserInfoProvider.getLoggedUser().getBranch().getBranchPK();
        OutboundBolPK outboundBolPK = new OutboundBolPK(branchPK.getCompanyId(), branchPK.getId(), bolId);
        BillOfLading billOfLading = outboundBolService.findBillOfLading(outboundBolPK);
        if(billOfLading!=null){
            ModelAndView mav = new ModelAndView("outboundBolPdf");
            mav.addObject("outboundBol", billOfLading);
            return mav;
        }else{
            ModelAndView mav = new ModelAndView("billOfLadingNotExist");
            mav.addObject("bolId", bolId);
            return mav;
        }
    }
    
}
