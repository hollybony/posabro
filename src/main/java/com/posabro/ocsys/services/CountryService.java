/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Country;
import java.util.List;

/**
 * Contains all the service methods of <code>Country</code>s
 * 
 * @author Carlos Juarez
 */
public interface CountryService {
    
    /**
     * Finds a country by id
     * 
     * @param id - the id with which the country is looked for
     * @return the countries found
     */
    public Country findCountry(String id);
    
    /**
     * Gets all the countries
     * 
     * @return the countries found
     */
    public List<Country> getAllCountries();
    
    /**
     * Saves a country
     * 
     * @param country - the country to save
     */
    public void saveCountry(Country country);
    
    /**
     * Updates the country
     * 
     * @param country - the country to update
     */
    public void updateCountry(Country country);
    
    /**
     * Removes a country
     * 
     * @param id - the id of the country to be removed
     */
    public void removeCountry(String id);

}
