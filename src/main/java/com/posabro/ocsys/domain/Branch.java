/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="BRANCHES")
public class Branch implements Serializable{
    
    @EmbeddedId
    private BranchId branchId;
    
    @MapsId("companyId")
    @ManyToOne
    @JoinColumn(name="COMPANY_ID", updatable=false, insertable=false)
    private Company company;

    public Branch(){
        this(null,null);
    }
    
    public Branch(String branchId, Company company){
        this.branchId = new BranchId(branchId, company==null?null:company.getName());
        this.company = company;
    }

    public BranchId getBranchId() {
        return branchId;
    }

    public void setBranchId(BranchId branchId) {
        this.branchId = branchId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
}
