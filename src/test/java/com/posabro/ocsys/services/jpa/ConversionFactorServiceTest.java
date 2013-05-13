/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorPK;
import com.posabro.ocsys.domain.UnitOfMeasurement;
import com.posabro.ocsys.services.ConversionFactorService;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Carlos Juarez
 */
public class ConversionFactorServiceTest extends AbstractServiceTest{
    
    private final ConversionFactor aConversionFactor = new ConversionFactor(UnitOfMeasurement.POUND, UnitOfMeasurement.LTS, 2);
    
    private final List<ConversionFactor> someConversionFactors = Arrays.asList(new ConversionFactor(UnitOfMeasurement.INCH, UnitOfMeasurement.CENTIMETER, 3),
            new ConversionFactor(UnitOfMeasurement.CENTIMETER, UnitOfMeasurement.INCH, 5), new ConversionFactor(UnitOfMeasurement.LTS, UnitOfMeasurement.POUND, 9));
    
    @Autowired
    private ConversionFactorService conversionFactorService;
    
    @Test
    public void testConversionFactorServiceUpdates(){
        //save
        conversionFactorService.saveConversionFactor(aConversionFactor);
        ConversionFactorPK conversionFactorId = new ConversionFactorPK(UnitOfMeasurement.POUND, UnitOfMeasurement.LTS);
        //find
        ConversionFactor foundConversionFactor = conversionFactorService.findConversionFactor(conversionFactorId);
        assertNotNull(foundConversionFactor);
        assertTrue(foundConversionFactor.getFactor()==2);
        assertEquals(UnitOfMeasurement.POUND, foundConversionFactor.getConversionFactorPK().getFromUnit());
        assertEquals(UnitOfMeasurement.LTS, foundConversionFactor.getConversionFactorPK().getToUnit());
        //remove
        conversionFactorService.removeConversionFactor(conversionFactorId);
        assertNull(conversionFactorService.findConversionFactor(conversionFactorId));
    }
    
    @Test
    public void testConversionFactorServiceQueries(){
        for(ConversionFactor conversionFactor : someConversionFactors){
            conversionFactorService.saveConversionFactor(conversionFactor);
        }
        List<ConversionFactor> allConversionFactors = conversionFactorService.getAllConversionFactors();
        assertTrue(someConversionFactors.size()==allConversionFactors.size());
        //cleanup
        for(ConversionFactor conversionFactor : allConversionFactors){
            conversionFactorService.removeConversionFactor(conversionFactor.getConversionFactorPK());
        }
    }
}
