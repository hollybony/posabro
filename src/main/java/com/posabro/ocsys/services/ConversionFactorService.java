/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorPK;
import java.util.List;

/**
 * Contains all the service methods of <code>ConversionFactor</code>s
 * 
 * @author Carlos Juarez
 */
public interface ConversionFactorService {
    
    /**
     * Finds a conversion factor by id
     * 
     * @param id - the id with which the conversion factor is looked for
     * @return the conversion factor found
     */
    public ConversionFactor findConversionFactor(ConversionFactorPK id);

    /**
     * Gets all the conversion factors
     * 
     * @return the conversion factors found
     */
    public List<ConversionFactor> getAllConversionFactors();

    /**
     * Saves a conversion factor
     * 
     * @param conversionFactor - the conversion factor to save
     */
    public void saveConversionFactor(ConversionFactor conversionFactor);

    /**
     * Updates a conversion factor
     * 
     * @param conversionFactor - the conversion factor to update
     */
    public void updateConversionFactor(ConversionFactor conversionFactor);

    /**
     * Removes a conversion factor
     * 
     * @param id - the id of the conversion factor to be removed
     */
    public void removeConversionFactor(ConversionFactorPK id);
    
}
