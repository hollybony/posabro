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
import com.posabro.ocsys.services.FacilityService;
import com.posabro.ocsys.services.StateService;
import com.posabro.services.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class FacilityServiceTest extends AbstractServiceTest{
    
    @Autowired
    private FacilityService facilityService;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private StateService stateService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CompanyService companyService;
    
    @Test
    public void testSaveFacility(){
        Country usa = new Country("US", "United States");
        countryService.saveCountry(usa);
        State delaware = new State("DE", "Delaware", usa);
        stateService.saveState(delaware);
        Company company = new Company("SAS", "Special Air");
        companyService.saveCompany(company);
        Customer customer = new Customer("CZ1", "Chavelo", "TX3", company);
        Address address = new Address("568 Christiana", "", 19713, "Newark", delaware);
        customer.setAddress(address);
        customerService.saveCustomer(customer);
        Facility facility = new Facility("F1", "CZ1", "SAS", "Dinner rom", address);
        facilityService.saveFacility(facility);
    }
    
}
