/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Carlos Juarez
 */
@Embeddable
public class FacilityId implements Serializable{
    
    @Column(name="ID", length=20)
    private String id;
    
    @Column(name="CUST_ID", length=20)
    private String customerId;
    
    @Column(name="COMPANY_ID", length=20)
    private String companyId;
    
    public FacilityId(){
        this(null,null,null);
    }
    
    public FacilityId(String id, String customerId, String companyId){
        this.id = id;
        this.customerId = customerId;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyId() {
        return companyId;
    }

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
        final FacilityId other = (FacilityId) obj;
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

}
