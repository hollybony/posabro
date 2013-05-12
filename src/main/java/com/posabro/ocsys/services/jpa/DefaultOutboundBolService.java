/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.domain.OutboundBol;
import com.posabro.ocsys.domain.OutboundBolPK;
import com.posabro.ocsys.repository.OutboundBolRepository;
import com.posabro.ocsys.services.BranchService;
import com.posabro.ocsys.services.OutboundBolService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Juarez
 */
@Service("outboundBolService")
@Repository
@Transactional
public class DefaultOutboundBolService implements OutboundBolService{
    
    @Autowired
    private OutboundBolRepository outboundBolRepository;
    
    @Autowired
    private BranchService branchService;

    @Override
    @Transactional(readOnly=true)
    public OutboundBol findOutboundBol(OutboundBolPK id) {
        return outboundBolRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<OutboundBol> getAllOutboundBols() {
        return Lists.newArrayList(outboundBolRepository.findAll());
    }

    @Override
    public void saveOutboundBol(BranchPK branchPK, OutboundBol outboundBol) {
        String bolId = branchService.generateNewConsecutive(branchPK);
        outboundBol.getOutboundBolPK().setId(bolId);
        outboundBolRepository.save(outboundBol);
    }

    @Override
    public void removeOutboundBol(OutboundBolPK id) {
        outboundBolRepository.delete(id);
    }
    
}
