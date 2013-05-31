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
 * Contains all the service methods of <code>Branch</code>s
 * 
 * @author Carlos Juarez
 */
public interface BranchService {
    
    /**
     * Finds a branch by id
     * 
     * @param id - the id with which the branch is looked for
     * @return 
     */
    public Branch findBranch(BranchPK id);
    
    /**
     * Finds all the branches
     * 
     * @param pageable - which is applied to the found branches
     * @return the branches found
     */
    public Page<Branch> findAll(Pageable pageable);
    
    /**
     * Gets all the branches
     * 
     * @return branches found
     */
    public List<Branch> getAllBranches();
    
    /**
     * Saves a brach
     * 
     * @param branch - the branch to save
     */
    public void saveBranch(Branch branch);
    
    /**
     * Update a branch
     * 
     * @param branch - the branch to update
     */
    public void updateBranch(Branch branch);
    
    /**
     * Removes a branch
     * 
     * @param id - id of the branch to be removed
     */
    public void removeBranch(BranchPK id);
    
    /**
     * Generates an new consecutive for the branch with the id given
     * 
     * @param id - the primary key of the branch for which the consecutive is generated
     * @return the newest boldId for the branch given
     */
    public String generateNewConsecutive(BranchPK id);
    
}
