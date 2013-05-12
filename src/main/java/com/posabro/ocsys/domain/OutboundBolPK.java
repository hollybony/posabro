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
public class OutboundBolPK implements Serializable{
    
    @Column(name="COMPANY_ID", length=20)
    private String companyId;
    
    @Column(name="BRANCH_ID", length=20)
    private String branchId;
    
    @Column(name="BOL_ID", length=20)
    private String id;
    
    public OutboundBolPK(){
        this(null, null, null);
    }
    
    public OutboundBolPK(String companyId, String branchId, String id){
        this.companyId = companyId;
        this.branchId = branchId;
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getId() {
        return id;
    }

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
