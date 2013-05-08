/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.services.CountryService;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class CountryServiceTest extends AbstractServiceTest{
    
    private final Country aCountry = new Country("COL", "Colombia");
    
    private final List<Country> someCountries = Arrays.asList(new Country("SPA", "Spain"),
            new Country("FRA", "France"), new Country("GER", "Germany"));
    
    @Autowired
    public CountryService countryService;
    
    @Test
    public void testCountryServiceUpdates(){
        //save
        countryService.saveCountry(aCountry);
        //find
        Country foundCountry = countryService.findCountry("COL");
        assertNotNull(foundCountry);
        assertEquals("Colombia", foundCountry.getName());
        //remove
        countryService.removeCountry("COL");
        assertNull(countryService.findCountry("COL"));
    }
    
    @Test
    public void testCountryServiceQueries(){
        for(Country country : someCountries){
            countryService.saveCountry(country);
        }
        List<Country> allCountries = countryService.getAllCountries();
        assertTrue(someCountries.size()==allCountries.size());
        //cleanup
        for(Country country : allCountries){
            countryService.removeCountry(country.getId());
        }
    }
    
}
