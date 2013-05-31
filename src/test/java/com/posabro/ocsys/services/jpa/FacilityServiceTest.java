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
import com.posabro.ocsys.domain.StatePK;
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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class FacilityServiceTest extends AbstractServiceTest{
    
    private final Country usa = new Country("US", "United States");
    
    private final State delaware = new State("DE", "Delaware", usa);
    
    private final Company company = new Company("SAS", "Special Air");
    
    private final Address address = new Address("568 Christiana", "", 19713, "Newark", delaware);
    
    private final Customer chavelo = new Customer("CZ1", "Chavelo", "TX3", company, address);
    
    private final Customer cantinflas = new Customer("CZ2", "Cantinflas", "TX1", company, address);
    
    private final List<Facility> someFacilities = Lists.newArrayList(
            new Facility("F1", "CZ2", "SAS", "Facility 1", address),
            new Facility("F2", "CZ1", "SAS", "Facility 2", address),
            new Facility("F3", "CZ2", "SAS", "Facility 3", address));
    
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
    public void testFacilityServiceUpdates(){
        countryService.saveCountry(usa);
        stateService.saveState(delaware);
        companyService.saveCompany(company);
        customerService.saveCustomer(chavelo);
        //save
        Facility facility = new Facility("F1", "CZ1", "SAS", "Dinner rom", address);
        facilityService.saveFacility(facility);
        FacilityPK facilityId = new FacilityPK("F1", "CZ1", "SAS");
        //find
        Facility foundFacility = facilityService.findFacilityById(facilityId);
        assertNotNull(foundFacility);
        assertEquals("Dinner rom", foundFacility.getName());
        //remove
        facilityService.removeFacility(facilityId);
        assertNull(facilityService.findFacilityById(facilityId));
        //cleanup
        customerService.removeCustomer(new CustomerPK("CZ1","SAS"));
        companyService.removeCompany("SAS");
        stateService.removeState(new StatePK("DE", "US"));
        countryService.removeCountry("US");
    }
    
    @Test
    public void testFacilityServiceQueries(){
        countryService.saveCountry(usa);
        stateService.saveState(delaware);
        companyService.saveCompany(company);
        customerService.saveCustomer(chavelo);
        customerService.saveCustomer(cantinflas);
        for(Facility facility : someFacilities){
            facilityService.saveFacility(facility);
        }
        List<Facility> allFacilities = facilityService.getAllFacilities();
        assertTrue(someFacilities.size()==allFacilities.size());
        //findFacilityByCustomerId
        List<Facility> cantinflasFacilities = facilityService.findFacilitiesByCustomer(new CustomerPK("CZ2", "SAS"));
        
        assertTrue(cantinflasFacilities.size()==2);
        assertEquals("Facility 1", cantinflasFacilities.get(0).getName());
        assertEquals("Facility 3", cantinflasFacilities.get(1).getName());
        
        //cleanup
        for(Facility facility : allFacilities){
            facilityService.removeFacility(facility.getFacilityPK());
        }
        customerService.removeCustomer(new CustomerPK("CZ1","SAS"));
        customerService.removeCustomer(new CustomerPK("CZ2","SAS"));
        companyService.removeCompany("SAS");
        stateService.removeState(new StatePK("DE", "US"));
        countryService.removeCountry("US");
    }
    
}
