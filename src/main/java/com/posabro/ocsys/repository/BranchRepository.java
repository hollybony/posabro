/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchPK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>Branch</code>s
 * 
 * @author Carlos Juarez
 */
public interface BranchRepository extends PagingAndSortingRepository<Branch, BranchPK> {
    
}
