/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityPK;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Contains all the repository methods related to Facility
 * 
 * @author Carlos Juarez
 */
public interface FacilityRepository extends PagingAndSortingRepository<Facility, FacilityPK> {
    
    /**
     * Deletes the facility of the given customer
     * 
     * @param customerId - the customerId to be matched
     * @param companyId  - the companyId to be matched
     */
    @Modifying
    @Query("delete from Facility f where f.facilityPK.customerId=:customerId and f.facilityPK.companyId=:companyId")
    public void deleteFacilitiesByCustomer(@Param("customerId")String customerId, @Param("companyId")String companyId);

    /**
     * Finds the facilities that match to the customerId and companyId given
     * 
     * @param customerId - the customerId to be matched
     * @param companyId - the companyId to be matched
     * @return the facilities found
     */
    public List<Facility> findByFacilityPK_CustomerIdAndFacilityPK_CompanyId(String customerId, String companyId);

}
