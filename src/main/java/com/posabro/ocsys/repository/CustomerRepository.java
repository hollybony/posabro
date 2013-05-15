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
 *
 * @author Carlos Juarez
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer,CustomerPK>{
    
    public List<Customer> findByCustomerPK_CompanyId(String companyId);
    

}
