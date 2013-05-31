/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityPK;
import com.posabro.ocsys.repository.FacilityRepository;
import com.posabro.ocsys.services.FacilityService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>FacilityService</code>
 * 
 * @author Carlos Juarez
 */
@Service("facilityService")
@Repository
@Transactional
public class DefaultFacilityService implements FacilityService{
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultFacilityService.class);

    /**
     * The facilityRepository
     */
    @Autowired
    private FacilityRepository facilityRepository;
    
    /**
     * @see FacilityService#findFacilityById(com.posabro.ocsys.domain.FacilityPK) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public Facility findFacilityById(FacilityPK id) {
        return facilityRepository.findOne(id);
    }

    /**
     * @see FacilityService#findFacilitiesByCustomer(com.posabro.ocsys.domain.CustomerPK) 
     * 
     * @param customerPK
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Facility> findFacilitiesByCustomer(CustomerPK customerPK) {
        return facilityRepository.findByFacilityPK_CustomerIdAndFacilityPK_CompanyId(customerPK.getId(), customerPK.getCompanyId());
    }
    
    /**
     * @see FacilityService#getAllFacilities() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Facility> getAllFacilities() {
        return Lists.newArrayList(facilityRepository.findAll());
    }

    /**
     * @see FacilityService#saveFacility(com.posabro.ocsys.domain.Facility) 
     * 
     * @param facility 
     */
    @Override
    public void saveFacility(Facility facility) {
        if(!facilityRepository.exists(facility.getFacilityPK())){
            facilityRepository.save(facility);
        }else{
            throw new JpaSystemException(new PersistenceException("facility " + facility.getFacilityPK() + " already exists"));
        }
    }

    /**
     * @see FacilityService#updateFacility(com.posabro.ocsys.domain.Facility) 
     * 
     * @param facility 
     */
    @Override
    public void updateFacility(Facility facility) {
        facilityRepository.save(facility);
    }

    /**
     * @see FacilityService#removeFacility(com.posabro.ocsys.domain.FacilityPK) 
     * 
     * @param id 
     */
    @Override
    public void removeFacility(FacilityPK id) {
        facilityRepository.delete(id);
    }
    
}
