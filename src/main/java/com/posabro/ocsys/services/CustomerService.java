/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerPK;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface CustomerService {
    
    public Customer findCustomer(CustomerPK id);
    
    public List<Customer> findCustomersByCompany(String companyId);
    
    public List<Customer> getAllCustomers();
    
    public void saveCustomer(Customer customer);
    
    public void updateCustomer(Customer customer);
    
    public void removeCustomer(CustomerPK id);
    
}
