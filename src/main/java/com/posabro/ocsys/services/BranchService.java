/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchPK;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Carlos Juarez
 */
public interface BranchService {
    
    public Branch findBranch(BranchPK id);
    
    public Page<Branch> findAll(Pageable pageable);
    
    public List<Branch> getAllBranches();
    
    public void saveBranch(Branch branch);
    
    public void updateBranch(Branch branch);
    
    public void removeBranch(BranchPK id);
    
    public String generateNewConsecutive(BranchPK id);
    
}
