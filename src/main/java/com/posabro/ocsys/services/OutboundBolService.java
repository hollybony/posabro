/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.BillOfLading;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import java.util.List;

/**
 * Contains all the service methods of <code>OutboundBol</code>s
 * 
 * @author Carlos Juarez
 */
public interface OutboundBolService {
    
    /**
     * Finds an outbound boL by id
     * @param id - the id with which the outbound boL is looked for
     * @return the outbound boL found
     */
    public OutboundBol findOutboundBol(OutboundBolPK id);
    
    public BillOfLading findBillOfLading(OutboundBolPK id);
    
    /**
     * Gets all the outbound boLs
     * 
     * @return the outbound boLs found
     */
    public List<OutboundBol> getAllOutboundBols();
    
    /**
     * Saves an outbound boL
     * 
     * @param branchPK - the primary key of the branch that will own the outbound boL
     * @param outboundBol - the outbound boL to save
     * @return the outbound boL id generated
     */
    public String saveOutboundBol(BranchPK branchPK, OutboundBol outboundBol);
    
    /**
     * Removes an outbound boL
     * @param id - the id of the outbound boL to be removed
     */
    public void removeOutboundBol(OutboundBolPK id);
    
}
