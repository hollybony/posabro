/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityId;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface FacilityService {
    
    public Facility findFacility(FacilityId id);
    
    public List<Facility> getAllFacilities();
    
    public void saveFacility(Facility facility);
    
    public void updateFacility(Facility facility);
    
    public void removeFacility(FacilityId id);
    
}
