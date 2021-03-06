/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorPK;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>ConversionFactor</code>s
 * 
 * @author Carlos Juarez
 */
public interface ConversionFactorRepository extends PagingAndSortingRepository<ConversionFactor, ConversionFactorPK> {
    
}
