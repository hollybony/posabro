/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.repository.CountryRepository;
import com.posabro.ocsys.services.CountryService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>CountryService</code>
 * 
 * @author Carlos Juarez
 */
@Service("countryService")
@Repository
@Transactional
public class DefaultCountryService implements CountryService{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultCountryService.class);
    
    /**
     * The countryRepository
     */
    @Autowired
    private CountryRepository countryRepository;
    
    /**
     * @see CountryService#findCountry(java.lang.String) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public Country findCountry(String id) {
        return countryRepository.findOne(id);
    }

    /**
     * @see CountryService#getAllCountries() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Country> getAllCountries() {
        return Lists.newArrayList(countryRepository.findAll());
    }

    /**
     * @see CountryService#saveCountry(com.posabro.ocsys.domain.Country) 
     * 
     * @param country 
     */
    @Override
    public void saveCountry(Country country) {
        if(!countryRepository.exists(country.getId())){
            countryRepository.save(country);
        }else{
            throw new JpaSystemException(new PersistenceException("country " + country.getId() + " already exists"));
        }
    }

    /**
     * @see CountryService#updateCountry(com.posabro.ocsys.domain.Country) 
     * 
     * @param country 
     */
    @Override
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    /**
     * @see CountryService#removeCountry(java.lang.String) 
     * 
     * @param id 
     */
    @Override
    public void removeCountry(String id) {
        countryRepository.delete(id);
    }
    
}
