/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StateId;
import com.posabro.ocsys.services.CountryService;
import com.posabro.ocsys.services.StateService;
import com.posabro.services.AbstractServiceTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class StateServiceTest extends AbstractServiceTest{
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CountryService countryService;
    @Test
    public void testSaveState(){
        Country country = new Country("MX","Mexico");
        countryService.saveCountry(country);
        //------
        State state = new State("SLP","San Luis Potosi",country);
        stateService.saveState(state);
        State foundState = stateService.findState(new StateId("SLP", "MX"));
        assertNotNull(foundState);
        assertEquals("San Luis Potosi", state.getName());
    }
    
}
