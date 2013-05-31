/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Carrier;
import java.util.List;

/**
 * Contains all the service methods of <code>Carrier</code>s
 * 
 * @author Carlos Juarez
 */
public interface CarrierService {
    
    /**
     * Finds a carrier by id
     * 
     * @param id - the id with which the carrier is looked for
     * @return the carrier found
     */
    public Carrier findCarrier(String id);
    
    /**
     * Gets all the carriers
     * 
     * @return the carriers found
     */
    public List<Carrier> getAllCarriers();
    
    /**
     * Saves a carrier
     * 
     * @param carrier - the carrier to save
     */
    public void saveCarrier(Carrier carrier);
    
    /**
     * Updates a carrier
     * 
     * @param carrier - the carrier to update
     */
    public void updateCarrier(Carrier carrier);
    
    /**
     * Removes a carrier
     * 
     * @param id - id of the carrier to be removed
     */
    public void removeCarrier(String id);
    
}
