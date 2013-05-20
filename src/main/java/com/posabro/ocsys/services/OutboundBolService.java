/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface OutboundBolService {
    
    public OutboundBol findOutboundBol(OutboundBolPK id);
    
    public List<OutboundBol> getAllOutboundBols();
    
    public String saveOutboundBol(BranchPK branchPK, OutboundBol outboundBol);
        
    public void removeOutboundBol(OutboundBolPK id);
    
}
