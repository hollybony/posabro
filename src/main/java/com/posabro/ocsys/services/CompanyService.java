/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Company;
import java.util.List;

/**
 * Contains all the service methods of <code>Company</code>s
 * 
 * @author Carlos Juarez
 */
public interface CompanyService {
    
    /**
     * Finds a company by id
     * 
     * @param id - the id with which the company is looked for
     * @return 
     */
    public Company findCompany(String id);
    
    /**
     * Gets all the companies
     * 
     * @return the companies found
     */
    public List<Company> getAllCompanies();
    
    /**
     * Saves a company
     * 
     * @param company - the company to save
     */
    public void saveCompany(Company company);
    
    /**
     * Updates the company
     * 
     * @param company - the company to update
     */
    public void updateCompany(Company company);
    
    /**
     * Removes a company
     * 
     * @param id - the id of the company to be removed
     */
    public void removeCompany(String id);
    
}
