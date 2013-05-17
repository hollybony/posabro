/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchPK;
import com.posabro.ocsys.repository.BranchRepository;
import com.posabro.ocsys.services.BranchService;
import java.util.Calendar;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Branch findBranch(BranchPK id) {
        return branchRepository.findOne(id);
    }
    
    @Override
    public Page<Branch> findAll(Pageable pageable) {
        return branchRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Branch> getAllBranches() {
        return Lists.newArrayList(branchRepository.findAll());
    }

    @Override
    public void saveBranch(Branch branch) {
        branch.setCurrentYear(0);
        branch.setLastBolConsecutive(0);
        if(!branchRepository.exists(branch.getBranchPK())){
            branchRepository.save(branch);
        }else{
            throw new JpaSystemException(new PersistenceException("branch " + branch.getBranchPK() + " already exists"));
        }
    }

    @Override
    public void updateBranch(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public void removeBranch(BranchPK id) {
        branchRepository.delete(id);
    }

    @Override
    public String generateNewConsecutive(BranchPK id) {
        Branch foundBranch = branchRepository.findOne(id);
        if(foundBranch==null){
            throw new JpaSystemException(new PersistenceException("cannot generate new consecutive because branch : " + foundBranch.getBranchPK() + " does not exist"));
        }
        logger.debug("generating a new consecutive for brach : " + foundBranch);
        if(foundBranch.getLastBolConsecutive()==null){
            foundBranch.setLastBolConsecutive(0);
        }
        if(foundBranch.getCurrentYear()==null){
            foundBranch.setCurrentYear(0);
        }
        Integer systemYear = Calendar.getInstance().get(Calendar.YEAR);
        if(foundBranch.getLastBolConsecutive()<=0 || !foundBranch.getCurrentYear().equals(systemYear)){
            foundBranch.setCurrentYear(systemYear);
            foundBranch.setLastBolConsecutive(1);
        }else if(foundBranch.getCurrentYear().equals(systemYear)){
            foundBranch.setLastBolConsecutive(foundBranch.getLastBolConsecutive() + 1);
        }
        String strConsecutive = "000" + foundBranch.getLastBolConsecutive();
        String generatedConsecutive = "" + foundBranch.getCurrentYear() + strConsecutive.substring(strConsecutive.length()-4);
        logger.debug("the generated consecutive is : " + generatedConsecutive);
        return generatedConsecutive;
    }
    
}
