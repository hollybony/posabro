/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Carrier;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>Carrier</code>s
 * 
 * @author Carlos Juarez
 */
public interface CarrierRepository extends PagingAndSortingRepository<Carrier,String> {
    
}
