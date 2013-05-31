/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.repository.CompanyRepositosy;
import com.posabro.ocsys.services.CompanyService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>CompanyService</code>
 * 
 * @author Carlos Juarez
 */
@Service("companyService")
@Repository
@Transactional
public class DefaultCompanyService implements CompanyService{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultCompanyService.class);
    
    /**
     * The companyRepositosy
     */
    @Autowired
    private CompanyRepositosy companyRepositosy;
    
    /**
     * @see CompanyService#findCompany(java.lang.String) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public Company findCompany(String id) {
        return companyRepositosy.findOne(id);
    }

    /**
     * @see CompanyService#getAllCompanies() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Company> getAllCompanies() {
        return Lists.newArrayList(companyRepositosy.findAll());
    }

    /**
     * @see CompanyService#saveCompany(com.posabro.ocsys.domain.Company) 
     * 
     * @param company 
     */
    @Override
    public void saveCompany(Company company) {
        if(!companyRepositosy.exists(company.getId())){
            companyRepositosy.save(company);
        }else{
            throw new JpaSystemException(new PersistenceException("company " + company.getId() + " already exists"));
        }
    }

    /**
     * @see CompanyService#updateCompany(com.posabro.ocsys.domain.Company) 
     * 
     * @param company 
     */
    @Override
    public void updateCompany(Company company) {
        companyRepositosy.save(company);
    }

    /**
     * @see CompanyService#removeCompany(java.lang.String) 
     * 
     * @param id 
     */
    @Override
    public void removeCompany(String id) {
        companyRepositosy.delete(id);
    }
    
}
