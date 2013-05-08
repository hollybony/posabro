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
 *
 * @author Carlos Juarez
 */
@Service("countryService")
@Repository
@Transactional
public class DefaultCountryService implements CountryService{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultCountryService.class);
    
    @Autowired
    private CountryRepository countryRepository;
            
    @Override
    @Transactional(readOnly=true)
    public Country findCountry(String id) {
        return countryRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Country> getAllCountries() {
        return Lists.newArrayList(countryRepository.findAll());
    }

    @Override
    public void saveCountry(Country country) {
        if(!countryRepository.exists(country.getId())){
            countryRepository.save(country);
        }else{
            throw new JpaSystemException(new PersistenceException("country " + country.getId() + " already exists"));
        }
    }

    @Override
    public void updateCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void removeCountry(String id) {
        countryRepository.delete(id);
    }
    
}
