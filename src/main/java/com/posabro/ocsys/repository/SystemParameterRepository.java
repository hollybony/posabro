/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.SystemParameter;
import com.posabro.ocsys.domain.SystemParameterId;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Carlos Juarez
 */
public interface SystemParameterRepository extends PagingAndSortingRepository<SystemParameter, SystemParameterId> {
    
}
