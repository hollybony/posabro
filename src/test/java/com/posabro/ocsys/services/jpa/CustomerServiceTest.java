/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Address;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.services.CompanyService;
import com.posabro.ocsys.services.CountryService;
import com.posabro.ocsys.services.CustomerService;
import com.posabro.ocsys.services.StateService;
import com.posabro.services.AbstractServiceTest;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class CustomerServiceTest extends AbstractServiceTest{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private CountryService countryService;
    
    @Test
    public void testSaveCustomer(){
        logger.debug("init testSaveCustomer");
        Country argentina = new Country("AR","Argentina");
        countryService.saveCountry(argentina);
        State cordoba = new State("CO", "Cordoba", argentina);
        stateService.saveState(cordoba);
        Company posabro = new Company("PO", "POSABRO");
        companyService.saveCompany(posabro);
        
        
        Customer customer = new Customer("RG","Sergio Martinez","TX1", posabro);
        Address address = new Address("Arista 500", "", 19876, "Cordoba Cd", cordoba);
        Facility facility = new Facility("WH",customer.getCustomerId().getId(), customer.getCustomerId().getCompanyId(),"Almacen",address);
        customer.setAddress(address);
        customer.addFacility(facility);
        logger.debug("init saveCustomer");
        customerService.saveCustomer(customer);
        
//        Customer foundCustomer = customerService.findCustomer("RG");
//        assertNotNull(foundCustomer);
//        assertEquals(foundCustomer.getName(), "Robert Guerrero");
    }
}
