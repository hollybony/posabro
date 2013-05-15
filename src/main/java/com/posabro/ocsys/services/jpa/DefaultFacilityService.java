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
 *
 * @author Carlos Juarez
 */
@Service("facilityService")
@Repository
@Transactional
public class DefaultFacilityService implements FacilityService{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultFacilityService.class);

    @Autowired
    private FacilityRepository facilityRepository;
    
    @Override
    @Transactional(readOnly=true)
    public Facility findFacility(FacilityPK id) {
        return facilityRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Facility> findFacilitiesByCustomer(CustomerPK customerPK) {
        return facilityRepository.findByFacilityPK_CustomerIdAndFacilityPK_CompanyId(customerPK.getId(), customerPK.getCompanyId());
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Facility> getAllFacilities() {
        return Lists.newArrayList(facilityRepository.findAll());
    }

    @Override
    public void saveFacility(Facility facility) {
        if(!facilityRepository.exists(facility.getFacilityPK())){
            facilityRepository.save(facility);
        }else{
            throw new JpaSystemException(new PersistenceException("facility " + facility.getFacilityPK() + " already exists"));
        }
    }

    @Override
    public void updateFacility(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public void removeFacility(FacilityPK id) {
        facilityRepository.delete(id);
    }
    
}
