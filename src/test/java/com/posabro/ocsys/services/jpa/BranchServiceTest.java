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
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
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
            new Branch("BR1", aCompany),
            new Branch("BR2", aCompany),
            new Branch("BR3", aCompany),
            new Branch("BR4", aCompany),
            new Branch("BR5", aCompany),
            new Branch("BR6", aCompany),
            new Branch("BR7", aCompany));
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private CompanyService companyService;
    
    @Before
    public void loadData(){
        companyService.saveCompany(aCompany);
        for(Branch branch: someBranches){
            branchService.saveBranch(branch);
        }
    }
    
    @After
    public void removeData(){
        for(Branch branch: someBranches){
            branchService.removeBranch(branch.getBranchPK());
        }
        companyService.removeCompany(aCompany.getId());
    }

    @Test
    public void testBranchServiceUpdates() {
        Branch branch = new Branch("BR0", aCompany);
        //save
        branchService.saveBranch(branch);
        //find
        Branch foundBranch = branchService.findBranch(new BranchPK("BR0", aCompany.getId()));
        assertNotNull(foundBranch);
        assertEquals("BR0", foundBranch.getBranchPK().getId());
        assertEquals(aCompany.getId(), foundBranch.getBranchPK().getCompanyId());
        assertEquals(new Integer(0), foundBranch.getCurrentYear());
        assertEquals(new Integer(0), foundBranch.getLastBolConsecituve());
        //remove
        branchService.removeBranch(new BranchPK("BR0", aCompany.getId()));
        Branch removedBranch = branchService.findBranch(new BranchPK("BR0", aCompany.getId()));
        assertNull(removedBranch);
    }
    
    @Test
    public void testBranchServiceQueries(){
        for(Branch branch : someBranches){
            assertNotNull(branchService.findBranch(branch.getBranchPK()));   
        }
    }
    
    @Test
    public void testBranchServicePages(){
        Pageable pageable = new PageRequest(2, 2, Sort.Direction.DESC, "branchPK");
        Page<Branch> foundBranches = branchService.findAll(pageable);
        logger.debug("foundBranches : " + foundBranches.getContent());
        assertTrue("the size of the page is not the expected",foundBranches.getSize()==2);
        assertEquals("BR3 branch was expected", foundBranches.getContent().get(0).getBranchPK().getId(), "BR3");
        assertEquals("BR2 branch was expected", foundBranches.getContent().get(1).getBranchPK().getId(), "BR2");
    }
    
    @Test
    public void testGenerateNewConsecutive(){
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        logger.debug("testing generate new consecutive, current year : " + year);
        Branch branch = new Branch("BR9", aCompany);
        branchService.saveBranch(branch);
        String bolId = branchService.generateNewConsecutive(branch.getBranchPK());
        assertEquals(year + "0001", bolId);
        Branch foundBranch = branchService.findBranch(branch.getBranchPK());
        logger.debug("found branch : " + foundBranch);
        assertEquals(year, foundBranch.getCurrentYear());
        assertEquals(new Integer(1), foundBranch.getLastBolConsecituve());
        //cleanup
        branchService.removeBranch(foundBranch.getBranchPK());
    }
    
}
