/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents the primary key of <code>Branch</code>
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class BranchPK implements Serializable{
    
    /**
     * The id
     */
    @Column(name="BRANCH_ID", length=20)
    private String id;
    
    /**
     * The company id
     */
    @Column(name="COMPANY_ID", length=20)
    private String companyId;

    /**
     * Creates an instance of <code>BranchPK</code> class
     */
    public BranchPK(){
        this(null,null);
    }
    
    /**
     * Creates an instance of <code>BranchPK</code> class
     * 
     * @param id - the id to set
     * @param companyId - the companyId to set
     */
    public BranchPK(String id, String companyId){
        this.id = id;
        this.companyId = companyId;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the companyId
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId - the companyId to set
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.companyId != null ? this.companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BranchPK other = (BranchPK) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.companyId == null) ? (other.companyId != null) : !this.companyId.equals(other.companyId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BranchPK{" + "id=" + id + ", companyId=" + companyId + '}';
    }
    
}
