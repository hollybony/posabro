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
public class SystemParameterId implements Serializable{
    
    @Column(name="COMPANY_ID", length=20, insertable=false, updatable=false)
    private String companyId;
    
    @Column(name="BRANCH_ID", length=20, insertable=false, updatable=false)
    private String branchId;
    
    
    public SystemParameterId(){
        this(null,null);
    }
    
    public SystemParameterId(String companyId, String branchId){
        this.companyId = companyId;
        this.branchId = branchId;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.companyId != null ? this.companyId.hashCode() : 0);
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
        final SystemParameterId other = (SystemParameterId) obj;
        if ((this.companyId == null) ? (other.companyId != null) : !this.companyId.equals(other.companyId)) {
            return false;
        }
        if ((this.branchId == null) ? (other.branchId != null) : !this.branchId.equals(other.branchId)) {
            return false;
        }
        return true;
    }

}
