/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary key of <code>OutboundBol</code>
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class OutboundBolPK implements Serializable{
    
    /**
     * The companyId
     */
    @Column(name="COMPANY_ID", length=20)
    private String companyId;
    
    /**
     * The branchId
     */
    @Column(name="BRANCH_ID", length=20)
    private String branchId;
    
    /**
     * The id
     */
    @Column(name="BOL_ID", length=20)
    private String id;
    
    /**
     * Constructs an instance of <code>OutboundBolPK</code> class
     */
    public OutboundBolPK(){
        this(null, null, null);
    }
    
    /**
     * Constructs an instance of <code>OutboundBolPK</code> class
     * 
     * @param companyId - the companyId to set
     * @param branchId - the branchId to set
     * @param id - the branchId to set
     */
    public OutboundBolPK(String companyId, String branchId, String id){
        this.companyId = companyId;
        this.branchId = branchId;
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

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId - the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.branchId != null ? this.branchId.hashCode() : 0);
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
        final OutboundBolPK other = (OutboundBolPK) obj;
        if ((this.companyId == null) ? (other.companyId != null) : !this.companyId.equals(other.companyId)) {
            return false;
        }
        if ((this.branchId == null) ? (other.branchId != null) : !this.branchId.equals(other.branchId)) {
            return false;
        }
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
}
