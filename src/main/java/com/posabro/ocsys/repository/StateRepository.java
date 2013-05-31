/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StatePK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>State</code>s
 * 
 * @author Carlos Juarez
 */
public interface StateRepository extends PagingAndSortingRepository<State, StatePK> {
    
}
