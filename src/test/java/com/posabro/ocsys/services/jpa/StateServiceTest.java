/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StatePK;
import com.posabro.ocsys.services.CountryService;
import com.posabro.ocsys.services.StateService;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class StateServiceTest extends AbstractServiceTest{
    
    private final Country country = new Country("MX","Mexico");
    
    private final State slp = new State("SLP","San Luis Potosi",country);
    
    private final List<State> someStates = Arrays.asList(new State("CHI", "Chiapas", country),
            new State("OAX", "Oaxaca", country),
            new State("SHI", "Chihuahua", country),
            new State("GUE", "Guerrero", country));
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CountryService countryService;
    
    @Test
    public void testStateServiceUpdates(){
        countryService.saveCountry(country);
        //save
        stateService.saveState(slp);
        StatePK stateId = new StatePK("SLP", "MX");
        //find
        State foundState = stateService.findState(stateId);
        assertNotNull(foundState);
        assertEquals("San Luis Potosi", slp.getName());
        //remove
        stateService.removeState(stateId);
        assertNull(stateService.findState(stateId));
        //cleanup
        countryService.removeCountry("MX");
    }
    
    @Test
    public void testStateServiceQueries(){
        countryService.saveCountry(country);
        for(State state : someStates){
            stateService.saveState(state);
        }
        List<State> allStates = stateService.getAllStates();
        assertTrue(someStates.size()==allStates.size());
        for(State state : allStates){
            stateService.removeState(state.getStatePK());
        }
        //cleanup
        countryService.removeCountry("MX");
    }
    
}
