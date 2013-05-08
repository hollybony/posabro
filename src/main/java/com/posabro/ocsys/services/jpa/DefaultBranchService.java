/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchId;
import com.posabro.ocsys.repository.BranchRepository;
import com.posabro.ocsys.services.BranchService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Juarez
 */
@Service("branchService")
@Repository
@Transactional
public class DefaultBranchService implements BranchService{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultBranchService.class);
    
    @Autowired
    private BranchRepository branchRepository;
    
    @Override
    @Transactional(readOnly=true)
    public Branch findBranch(BranchId id) {
        return branchRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Branch> getAllBranches() {
        return Lists.newArrayList(branchRepository.findAll());
    }

    @Override
    public void saveBranch(Branch branch) {
        if(!branchRepository.exists(branch.getBranchId())){
            branchRepository.save(branch);
        }else{
            throw new JpaSystemException(new PersistenceException("branch " + branch.getBranchId() + " already exists"));
        }
    }

    @Override
    public void updateBranch(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public void removeBranch(BranchId id) {
        branchRepository.delete(id);
    }
    
}
