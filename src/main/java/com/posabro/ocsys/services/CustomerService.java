/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerPK;
import java.util.List;

/**
 * Contains all the service methods of <code>Customer</code>s
 * 
 * @author Carlos Juarez
 */
public interface CustomerService {
    
    /**
     * Finds a customer by id
     * 
     * @param id - the id with which the customer is looked for
     * @return 
     */
    public Customer findCustomer(CustomerPK id);
    
    /**
     * Finds customers that belong to the same company
     * 
     * @param companyId - the companyId to be matched
     * @return the customers found
     */
    public List<Customer> findCustomersByCompany(String companyId);
    
    /**
     * Gets all the customers
     * 
     * @return the customers found
     */
    public List<Customer> getAllCustomers();
    
    /**
     * Saves a customer
     * 
     * @param customer - the customer to save
     */
    public void saveCustomer(Customer customer);
    
    /**
     * Updates a customer
     * 
     * @param customer - the customer to update
     */
    public void updateCustomer(Customer customer);
    
    /**
     * Removes a customer
     * 
     * @param id - the id of the customer to be removed
     */
    public void removeCustomer(CustomerPK id);
    
}
