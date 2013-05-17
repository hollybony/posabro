/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="BRANCHES")
@SecondaryTable(name="SYSTEM_CONTROL_INFO",
pkJoinColumns={@PrimaryKeyJoinColumn(name="BRANCH_ID"),@PrimaryKeyJoinColumn(name="COMPANY_ID")})
public class Branch implements Serializable{
    
    @EmbeddedId
    private BranchPK branchPK;
    
    @MapsId("companyId")
    @ManyToOne
    @JoinColumn(name="COMPANY_ID", columnDefinition="VARCHAR(20)", updatable=false, insertable=false)
    private Company company;

    @Column(name="CURRENT_YEAR", table="SYSTEM_CONTROL_INFO", nullable=false)
    private Integer currentYear;
    
    @Column(name="LAST_BOL_SEQ", table="SYSTEM_CONTROL_INFO", nullable=false)
    private Integer lastBolConsecutive;

    public Branch(){
        this(null,null);
    }
    
    public Branch(String branchId, Company company){
        this.branchPK = new BranchPK(branchId, company==null?null:company.getId());
        this.company = company;
        this.currentYear = 0;
        this.lastBolConsecutive = 0;
    }


    public BranchPK getBranchPK() {
        return branchPK;
    }

    public void setBranchPK(BranchPK branchPK) {
        this.branchPK = branchPK;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public Integer getLastBolConsecutive() {
        return lastBolConsecutive;
    }

    public void setLastBolConsecutive(Integer lastBolConsecutive) {
        this.lastBolConsecutive = lastBolConsecutive;
    }

    @Override
    public String toString() {
        return "Branch{" + "branchPK=" + branchPK + ", company=" + company + ", currentYear=" + currentYear + ", lastBolConsecutive=" + lastBolConsecutive + '}';
    }
    
}
