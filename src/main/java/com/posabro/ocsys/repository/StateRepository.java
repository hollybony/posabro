/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StateId;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Carlos Juarez
 */
public interface StateRepository extends PagingAndSortingRepository<State, StateId> {
    
}
