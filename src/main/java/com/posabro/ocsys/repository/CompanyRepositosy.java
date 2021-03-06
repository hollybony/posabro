/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>Company</code>s
 * 
 * @author Carlos Juarez
 */
public interface CompanyRepositosy extends PagingAndSortingRepository<Company, String> {
    
}
