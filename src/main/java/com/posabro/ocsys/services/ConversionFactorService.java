/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorId;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface ConversionFactorService {
    
    public ConversionFactor findConversionFactor(ConversionFactorId id);

    public List<ConversionFactor> getAllConversionFactors();

    public void saveConversionFactor(ConversionFactor conversionFactor);

    public void updateConversionFactor(ConversionFactor conversionFactor);

    public void removeConversionFactor(ConversionFactorId id);
    
}
