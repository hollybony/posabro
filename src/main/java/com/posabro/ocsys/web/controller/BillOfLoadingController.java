/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.web.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Juarez
 */
@Controller
@RequestMapping("/billOfLoadingController/*")
public class BillOfLoadingController extends ValidationController{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(BillOfLoadingController.class);
    
    @Autowired
    private OutboundBolService outboundBolService;
    
    @RequestMapping(value="storeOutboundBol", method = RequestMethod.POST)
    public void store(@Valid @RequestBody OutboundBol outboundBol){
        BranchPK branchPK = UserInfoProvider.getLoggedUser().getBranch().getBranchPK();
        outboundBolService.saveOutboundBol(branchPK, outboundBol);
    }
    
    @RequestMapping(value="outboundBolToPdf", method = RequestMethod.GET)
    public ModelAndView exportOutboundBol(@Valid @RequestBody OutboundBolPK outboundBolPK){
        OutboundBol foundOtboundBol = outboundBolService.findOutboundBol(outboundBolPK);
        ModelAndView mav = new ModelAndView("outboundBolPdf");
        mav.addObject("outboundBol", foundOtboundBol);
        return mav;
    }
    
}
