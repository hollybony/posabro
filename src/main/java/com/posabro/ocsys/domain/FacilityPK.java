/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary key of <code>Facility</code>
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class FacilityPK implements Serializable{
    
    /**
     * The id
     */
    @Column(name="FACILITY_ID", length=20)
    private String id;
    
    /**
     * The customerId
     */
    @Column(name="CUST_ID", length=20)
    private String customerId;
    
    /**
     * The companyId
     */
    @Column(name="COMPANY_ID", length=20)
    private String companyId;
    
    /**
     * Constructs an instance of <code>FacilityPK</code> class
     */
    public FacilityPK(){
        this(null,null,null);
    }
    
    /**
     * Constructs an instance of <code>FacilityPK</code> class
     * 
     * @param id - the id to set
     * @param customerId - the customerId to set
     * @param companyId - the companyId to set
     */
    public FacilityPK(String id, String customerId, String companyId){
        this.id = id;
        this.customerId = customerId;
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
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId - the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
        hash = 47 * hash + (this.customerId != null ? this.customerId.hashCode() : 0);
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
        final FacilityPK other = (FacilityPK) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.customerId == null) ? (other.customerId != null) : !this.customerId.equals(other.customerId)) {
            return false;
        }
        if ((this.companyId == null) ? (other.companyId != null) : !this.companyId.equals(other.companyId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FacilityPK{" + "id=" + id + ", customerId=" + customerId + ", companyId=" + companyId + '}';
    }

}
