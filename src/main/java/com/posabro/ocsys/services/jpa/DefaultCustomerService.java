/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerId;
import com.posabro.ocsys.repository.CustomerRepository;
import com.posabro.ocsys.services.CustomerService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Service("customerService")
@Repository
@Transactional
public class DefaultCustomerService implements CustomerService{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultCustomerService.class);
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly=true)
    public Customer findCustomer(CustomerId id) {
        return customerRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Customer> getAllCustomers() {
        return Lists.newArrayList(customerRepository.findAll());
    }

    @Override
    public void saveCustomer(Customer customer) {
//        if(em.find(Customer.class, new CustomerId(customer.getId(), customer.getCompany().getId()))==null){
//            customer.setCompany(em.find(Company.class, customer.getCompany().getId()));
//            customer.getAddress().getState().setCountry(em.find(Country.class, customer.getAddress().getState().getCountry().getId()));
//            em.persist(customer);
////            em.merge(customer);
//        }
        
        if(!customerRepository.exists(customer.getCustomerId())){
            customerRepository.save(customer);
        }
        else{
            throw new JpaSystemException(new PersistenceException("customer " + customer.getCustomerId() + " already exists"));
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void removeCustomer(CustomerId id) {
        customerRepository.delete(id);
    }
    
}
