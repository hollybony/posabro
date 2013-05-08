/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchId;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.services.BranchService;
import com.posabro.ocsys.services.CompanyService;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
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
public class BranchServiceTest extends AbstractServiceTest {

    private final Company aCompany = new Company("SOF", "Son of Family");
    
    private final List<Branch> someBranches = Arrays.asList(new Branch("BR1", aCompany), new Branch("BR2", aCompany), new Branch("BR3", aCompany));
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private CompanyService companyService;

    @Test
    public void testBranchServiceUpdates() {
        companyService.saveCompany(aCompany);
        Branch branch = new Branch("BR1", aCompany);
        //save
        branchService.saveBranch(branch);
        //find
        Branch foundBranch = branchService.findBranch(new BranchId("BR1", aCompany.getId()));
        assertNotNull(foundBranch);
        assertEquals("BR1", foundBranch.getBranchId().getId());
        assertEquals(aCompany.getId(), foundBranch.getBranchId().getCompanyId());
        //remove
        branchService.removeBranch(new BranchId("BR1", aCompany.getId()));
        Branch removedBranch = branchService.findBranch(new BranchId("BR1", aCompany.getId()));
        assertNull(removedBranch);
        //cleanup
        companyService.removeCompany(aCompany.getId());
    }
    
    @Test
    public void testBranchServiceQueries(){
        companyService.saveCompany(aCompany);
        for(Branch branch: someBranches){
            branchService.saveBranch(branch);
        }
        List<Branch> allBranches = branchService.getAllBranches();
        assertTrue(allBranches.size()==someBranches.size());
        //cleanup
        for(Branch branch: allBranches){
            branchService.removeBranch(branch.getBranchId());
        }
        companyService.removeCompany(aCompany.getId());
    }
    
}
