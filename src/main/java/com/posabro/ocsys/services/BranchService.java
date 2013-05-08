/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchId;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface BranchService {
    
    public Branch findBranch(BranchId id);
    
    public List<Branch> getAllBranches();
    
    public void saveBranch(Branch branch);
    
    public void updateBranch(Branch branch);
    
    public void removeBranch(BranchId id);
    
}
