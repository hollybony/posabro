/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Branch;
import com.posabro.ocsys.domain.Company;
import com.posabro.ocsys.domain.SystemParameter;
import com.posabro.ocsys.services.BranchService;
import com.posabro.ocsys.services.CompanyService;
import com.posabro.ocsys.services.SystemParameterService;
import com.posabro.services.AbstractServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class SystemParameterServiceTest extends AbstractServiceTest{
    
    @Autowired
    private SystemParameterService systemParameterService;
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private CompanyService companyService;
    
    @Test
    public void testSaveSystemParameter(){
        Company company = new Company("SIS", "Silicon Integrated Service Corp");
        companyService.saveCompany(company);
        Branch branch = new Branch("BR1", company);
        branchService.saveBranch(branch);
        SystemParameter systemParameter = new SystemParameter(12, 24, branch);
        systemParameterService.saveSystemParameter(systemParameter);
    }
    
}
