/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Address;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.Customer;
import com.posabro.ocsys.domain.CustomerPK;
import com.posabro.ocsys.domain.Facility;
import com.posabro.ocsys.domain.FacilityPK;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.services.CompanyService;
import com.posabro.ocsys.services.CountryService;
import com.posabro.ocsys.services.CustomerService;
import com.posabro.ocsys.services.FacilityService;
import com.posabro.ocsys.services.StateService;
import com.posabro.services.AbstractServiceTest;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class CustomerServiceTest extends AbstractServiceTest{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
    
    private final Country argentina = new Country("AR","Argentina");
    
    private final State cordoba = new State("CO", "Cordoba", argentina);
        
    private final Address address = new Address("Gardel 500", "", 19876, "Cordoba Cd", cordoba);
    
    private final Company posabro = new Company("PO", "POSABRO");
    
    private final Company broCorp = new Company("CO", "Brothers corp");
    
    private final Customer aCustomer = new Customer("RG","Sergio Martinez","TX1", posabro, address);
    
    
    private final List<Customer> someCustomers = Lists.newArrayList(
            new Customer("C1", "Customer 1", "TX1", posabro, address),
            new Customer("C2", "Customer 2", "TX2", posabro, address),
            new Customer("C3", "Customer 3", "TX1", broCorp, address),
            new Customer("C4", "Customer 4", "TX4", posabro, address));
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private FacilityService facilityService;
    
    @Test
    public void testCustomerServiceUpdates(){
        logger.debug("init testSaveCustomer");
        countryService.saveCountry(argentina);
        stateService.saveState(cordoba);
        companyService.saveCompany(posabro);    
        Facility facility = new Facility("WH",aCustomer.getCustomerPK().getId(), aCustomer.getCustomerPK().getCompanyId(),"Almacen",address);
        aCustomer.addFacility(facility);
        logger.debug("init saveCustomer");
        //save
        customerService.saveCustomer(aCustomer);
        logger.debug("saving facility : " + facility);
        CustomerPK customerId = new CustomerPK("RG", "PO");
        //find
        Customer foundCustomer = customerService.findCustomer(customerId);
        assertNotNull(foundCustomer);
        assertEquals("Sergio Martinez", foundCustomer.getName());
        assertEquals("TX1", foundCustomer.getTaxId());
        //remove
        customerService.removeCustomer(customerId);
        assertNull(customerService.findCustomer(customerId));
        assertNull(facilityService.findFacility(new FacilityPK("WH", "RG", "PO")));
        //cleanup
        companyService.removeCompany(posabro.getId());
        stateService.removeState(cordoba.getStatePK());
        countryService.removeCountry(argentina.getId());
    }
    
    @Test
    public void testCustomerServiceQueries(){
        countryService.saveCountry(argentina);
        stateService.saveState(cordoba);
        companyService.saveCompany(posabro);
        companyService.saveCompany(broCorp);
        for(Customer customer : someCustomers){
            customerService.saveCustomer(customer);
        }
        List<Customer> allCustomers = customerService.getAllCustomers();
        assertTrue("size of someCustomers and allCustomers don't match", someCustomers.size()==allCustomers.size());
        //findCustomersByCompany
        logger.debug("finding customers By Company");
        List<Customer> coCustomers = customerService.findCustomersByCompany("CO");
        assertTrue("coCustomers of size 1 was expected",coCustomers.size()==1);
        assertEquals("Customer 3 was expected", coCustomers.get(0).getName(), "Customer 3");
        //cleanup
        for(Customer customer : allCustomers){
            customerService.removeCustomer(customer.getCustomerPK());
        }
        companyService.removeCompany(posabro.getId());
        companyService.removeCompany(broCorp.getId());
        stateService.removeState(cordoba.getStatePK());
        countryService.removeCountry(argentina.getId());
    }
    
}
