/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Address;
import com.posabro.ocsys.domain.Carrier;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.services.CarrierService;
import com.posabro.ocsys.services.CountryService;
import com.posabro.ocsys.services.StateService;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.AssertTrue;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class CarrierServiceTest extends AbstractServiceTest{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(CarrierServiceTest.class);
    
    private final Country peru = new Country("PE", "Peru");
    
    private final State lima = new State("LI", "Lima", peru);
    
    private final Address address = new Address("2da de arriba 170", "", 70130, "Tepexa", lima);
    
    private final List<Carrier> someCarriers = Arrays.asList(new Carrier("CA1","Carrier 1","TX1", address),
            new Carrier("CA2","Carrier 2","TX2", address),new Carrier("CA3","Carrier 2","TX3", address));
    
    @Autowired
    private CarrierService carrierService;
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CountryService countryService;
    
    @Test
    public void testCarrierServiceUpdates(){
        logger.debug("init testSaveCarrier");
        
        logger.debug("saving country");
        countryService.saveCountry(peru);
        
        logger.debug("saving state");
        stateService.saveState(lima); 
        Carrier carrier = new Carrier();
        carrier.setId("TR");
        carrier.setName("Transportes el peruano");
        carrier.setTaxId("D1");
        
        carrier.setAddress(address);
        //save
        carrierService.saveCarrier(carrier);
        //find
        Carrier foundCarrier = carrierService.findCarrier("TR");
        assertNotNull(foundCarrier);
        assertEquals("Transportes el peruano", foundCarrier.getName());
        assertEquals("D1", foundCarrier.getTaxId());
        assertEquals("TR", foundCarrier.getId());
        //remove
        carrierService.removeCarrier("TR");
        assertNull(carrierService.findCarrier("TR"));
        //cleanup
        stateService.removeState(lima.getStatePK());
        countryService.removeCountry(peru.getId());
    }
    
    @Test
    public void testCarrierServiceQueries(){
        countryService.saveCountry(peru);
        stateService.saveState(lima); 
        
        for(Carrier carrier : someCarriers){
            carrierService.saveCarrier(carrier);
        }
        List<Carrier> allCarriers = carrierService.getAllCarriers();
        assertTrue(allCarriers.size()==someCarriers.size());
        //cleanup
        for(Carrier carrier : allCarriers){
            carrierService.removeCarrier(carrier.getId());
        }
        stateService.removeState(lima.getStatePK());
        countryService.removeCountry(peru.getId());
    }
    
}
