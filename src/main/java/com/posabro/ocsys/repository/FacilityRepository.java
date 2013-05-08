/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityId;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Carlos Juarez
 */
public interface FacilityRepository extends PagingAndSortingRepository<Facility, FacilityId> {
    
}
