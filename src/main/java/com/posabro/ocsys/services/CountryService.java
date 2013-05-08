/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Country;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface CountryService {
    
    public Country findCountry(String id);
    
    public List<Country> getAllCountries();
    
    public void saveCountry(Country country);
    
    public void updateCountry(Country country);
    
    public void removeCountry(String id);

}
