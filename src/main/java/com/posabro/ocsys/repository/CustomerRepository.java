/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.repository;

import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerPK;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contains all the repository methods related to <code>Customer</code>s
 * 
 * @author Carlos Juarez
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer,CustomerPK>{
    
    /**
     * Finds Customers that belong to the companyId
     * 
     * @param companyId - the companyId to be matched
     * @return the customers found
     */
    public List<Customer> findByCustomerPK_CompanyId(String companyId);
    

}
