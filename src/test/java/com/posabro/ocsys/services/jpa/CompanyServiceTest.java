/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.services.CompanyService;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class CompanyServiceTest extends AbstractServiceTest{
    
    private final Company aCompany = new Company("LC","la chancla");
    
    private final List<Company> someCompanies = Arrays.asList(new Company("FRT", "Fear rare Trust"),new Company("RTC", "Radio Tele Control"),new Company("CAC", "Call a Called"));
    
    @Autowired
    private CompanyService companyService;
    
    @Test
    public void testCompanyServiceUpdates(){
        //save
        companyService.saveCompany(aCompany);
        //find
        Company foundCompany = companyService.findCompany(aCompany.getId());
        assertNotNull(foundCompany);
        assertEquals("LC", foundCompany.getId());
        assertEquals("la chancla", foundCompany.getName());
        companyService.removeCompany("LC");
        Company removedCompany = companyService.findCompany("LC");
        assertNull("It supposed this company were removed", removedCompany);
    }
    
    @Test
    public void testCompanyServiceQueries(){
        for(Company company : someCompanies){
            companyService.saveCompany(company);
        }
        List<Company> allCompanies = companyService.getAllCompanies();
        assertTrue("allCompanies and someCompanies don't have the same size",allCompanies.size()==someCompanies.size());
        //cleanup
        for(Company company : someCompanies){
            companyService.removeCompany(company.getId());
        }
    }
}
