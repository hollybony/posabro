/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Company;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface CompanyService {
    
    public Company findCompany(String id);
    
    public List<Company> getAllCompanies();
    
    public void saveCompany(Company company);
    
    public void updateCompany(Company company);
    
    public void removeCompany(String id);
    
}
