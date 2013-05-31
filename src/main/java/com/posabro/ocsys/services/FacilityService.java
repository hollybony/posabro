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
 * Contains all the service methods of <code>Facility</code>s
 * 
 * @author Carlos Juarez
 */
public interface FacilityService {
    
    /**
     * Finds facility by id
     * 
     * @param id - the id with which the facility is looked for
     * @return the facilities found
     */
    public Facility findFacilityById(FacilityPK id);
    
    /**
     * Finds facilities that belong to the customer given
     * 
     * @param customerPK - the customer primary key to be matched
     * @return the facilities found
     */
    public List<Facility> findFacilitiesByCustomer(CustomerPK customerPK);
    
    /**
     * Gets all the facilities
     * 
     * @return the facilities found
     */
    public List<Facility> getAllFacilities();
    
    /**
     * Saves a facility
     * 
     * @param facility - the facility to save
     */
    public void saveFacility(Facility facility);
    
    /**
     * Updates a facility
     * 
     * @param facility - the facility to update
     */
    public void updateFacility(Facility facility);
    
    /**
     * Removes a facility
     * 
     * @param id - the id of the facility to be removed
     */
    public void removeFacility(FacilityPK id);
    
}
