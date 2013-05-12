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
public class BranchPK implements Serializable{
    
    @Column(name="BRANCH_ID", length=20)
    private String id;
    
    @Column(name="COMPANY_ID", length=20)
    private String companyId;

    public BranchPK(){
        this(null,null);
    }
    
    public BranchPK(String id, String companyId){
        this.id = id;
        this.companyId = companyId;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
