/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.BranchPK;
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
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Carlos Juarez
 */
public class BranchServiceTest extends AbstractServiceTest {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(BranchServiceTest.class);

    private final Company aCompany = new Company("SOF", "Son of Family");
    
    private final List<Branch> someBranches = Arrays.asList(
            new Branch("BR1", aCompany, 1, 2),
            new Branch("BR2", aCompany, 1, 2),
            new Branch("BR3", aCompany, 1, 2),
            new Branch("BR4", aCompany, 1, 2),
            new Branch("BR5", aCompany, 1, 2),
            new Branch("BR6", aCompany, 1, 2),
            new Branch("BR7", aCompany, 1, 2));
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private CompanyService companyService;

    @Test
    public void testBranchServiceUpdates() {
        companyService.saveCompany(aCompany);
        Branch branch = new Branch("BR1", aCompany, 3, 2);
        //save
        branchService.saveBranch(branch);
        //find
        Branch foundBranch = branchService.findBranch(new BranchPK("BR1", aCompany.getId()));
        assertNotNull(foundBranch);
        assertEquals("BR1", foundBranch.getBranchPK().getId());
        assertEquals(aCompany.getId(), foundBranch.getBranchPK().getCompanyId());
        assertEquals(3, foundBranch.getCurrentYear());
        assertEquals(2, foundBranch.getLastBolConsecituve());
        //remove
        branchService.removeBranch(new BranchPK("BR1", aCompany.getId()));
        Branch removedBranch = branchService.findBranch(new BranchPK("BR1", aCompany.getId()));
        assertNull(removedBranch);
        //cleanup
        companyService.removeCompany(aCompany.getId());
    }
    
    private void saveSomeBranches(){
        companyService.saveCompany(aCompany);
        for(Branch branch: someBranches){
            branchService.saveBranch(branch);
        }
    }
    
    private void removeSomeBranches(){
        for(Branch branch: someBranches){
            branchService.removeBranch(branch.getBranchPK());
        }
        companyService.removeCompany(aCompany.getId());
    }
    
    @Test
    public void testBranchServiceQueries(){
        saveSomeBranches();
        List<Branch> allBranches = branchService.getAllBranches();
        assertTrue(allBranches.size()==someBranches.size());
        //cleanup
        removeSomeBranches();
    }
    
    @Test
    public void testBranchServicePages(){
        saveSomeBranches();
        Pageable pageable = new PageRequest(2, 2, Sort.Direction.DESC, "branchPK");
        Page<Branch> foundBranches = branchService.findAll(pageable);
        logger.debug("foundBranches : " + foundBranches.getContent());
        assertTrue("the size of the page is not the expected",foundBranches.getSize()==2);
        assertEquals("BR3 branch was expected", foundBranches.getContent().get(0).getBranchPK().getId(), "BR3");
        assertEquals("BR2 branch was expected", foundBranches.getContent().get(1).getBranchPK().getId(), "BR2");
        removeSomeBranches();
    }
    
    @Test
    public void testGenerateNewConsecutive(){
        throw new UnsupportedOperationException("not implemented yet");
    }
    
}
