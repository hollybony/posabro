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
 * Represents a branch of a company
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="BRANCHES")
@SecondaryTable(name="SYSTEM_CONTROL_INFO",
pkJoinColumns={@PrimaryKeyJoinColumn(name="BRANCH_ID"),@PrimaryKeyJoinColumn(name="COMPANY_ID")})
public class Branch implements Serializable{
    
    /**
     * The primary key
     */
    @EmbeddedId
    private BranchPK branchPK;
    
    /**
     * The company owner of this branch
     */
    @MapsId("companyId")
    @ManyToOne
    @JoinColumn(name="COMPANY_ID", columnDefinition="VARCHAR(20)", updatable=false, insertable=false)
    private Company company;

    /**
     * The current year because branches can live in different times
     */
    @Column(name="CURRENT_YEAR", table="SYSTEM_CONTROL_INFO", nullable=false)
    private Integer currentYear;
    
    /**
     * The generated consecutive of the last bol processed for this branch
     */
    @Column(name="LAST_BOL_SEQ", table="SYSTEM_CONTROL_INFO", nullable=false)
    private Integer lastBolConsecutive;

    /**
     * Creates an instance of <code>Branch</code> class
     */
    public Branch(){
        this(null,null);
    }
    
    /**
     * Creates an instance of <code>Branch</code> class
     * 
     * @param branchId - branchId that will be part of the primary key
     * @param company  - company to set and whose id will be part of the primary key
     */
    public Branch(String branchId, Company company){
        this.branchPK = new BranchPK(branchId, company==null?null:company.getId());
        this.company = company;
        this.currentYear = 0;
        this.lastBolConsecutive = 0;
    }

    /**
     * @return the branchPK
     */
    public BranchPK getBranchPK() {
        return branchPK;
    }

    /**
     * @param branchPK - the branchPK to set
     */
    public void setBranchPK(BranchPK branchPK) {
        this.branchPK = branchPK;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company - the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the currentYear
     */
    public Integer getCurrentYear() {
        return currentYear;
    }

    /**
     * @param currentYear - the currentYear to set
     */
    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    /**
     * @return the lastBolConsecutive
     */
    public Integer getLastBolConsecutive() {
        return lastBolConsecutive;
    }

    /**
     * @param lastBolConsecutive the lastBolConsecutive
     */
    public void setLastBolConsecutive(Integer lastBolConsecutive) {
        this.lastBolConsecutive = lastBolConsecutive;
    }

    @Override
    public String toString() {
        return "Branch{" + "branchPK=" + branchPK + ", company=" + company + ", currentYear=" + currentYear + ", lastBolConsecutive=" + lastBolConsecutive + '}';
    }
    
}
