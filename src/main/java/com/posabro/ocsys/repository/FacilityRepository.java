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
 *
 * @author Carlos Juarez
 */
public interface FacilityRepository extends PagingAndSortingRepository<Facility, FacilityPK> {
    
    @Modifying
    @Query("delete from Facility f where f.facilityPK.customerId=:customerId and f.facilityPK.companyId=:companyId")
    public void deleteFacilitiesByCustomer(@Param("customerId")String customerId, @Param("companyId")String companyId);

    public List<Facility> findByFacilityPK_CustomerIdAndFacilityPK_CompanyId(String customerId, String companyId);

}
