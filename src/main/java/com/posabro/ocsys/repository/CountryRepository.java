/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>Country</code>s
 * 
 * @author Carlos Juarez
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
    
}
