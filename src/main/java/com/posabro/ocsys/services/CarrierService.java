/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Carrier;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface CarrierService {
    
    public Carrier findCarrier(String id);
    
    public List<Carrier> getAllCarriers();
    
    public void saveCarrier(Carrier carrier);
    
    public void updateCarrier(Carrier carrier);
    
    public void removeCarrier(String id);
    
}
