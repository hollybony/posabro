/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityPK;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface FacilityService {
    
    public Facility findFacility(FacilityPK id);
    
    public List<Facility> findFacilitiesByCustomer(CustomerPK customerPK);
    
    public List<Facility> getAllFacilities();
    
    public void saveFacility(Facility facility);
    
    public void updateFacility(Facility facility);
    
    public void removeFacility(FacilityPK id);
    
}
