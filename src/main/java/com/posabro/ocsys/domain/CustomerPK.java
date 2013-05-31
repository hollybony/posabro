/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary key of <code>Customer</code>
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class CustomerPK implements Serializable{
    
    /**
     * The id
     */
    @Column(name="CUST_ID", length=20)
    private String id;
    
    /**
     * The companyId
     */
    private String companyId;
    
    /**
     * Constructs an instance of <code>CustomerPK</code> class
     */
    public CustomerPK(){
        this(null,null);
    }
    
    /**
     * Constructs an instance of <code>CustomerPK</code> class
     * 
     * @param id - the id to set
     * @param companyId - the companyId to set
     */
    public CustomerPK(String id, String companyId){
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
        hash = 17 * hash + (this.companyId != null ? this.companyId.hashCode() : 0);
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
        final CustomerPK other = (CustomerPK) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.companyId == null) ? (other.companyId != null) : !this.companyId.equals(other.companyId)) {
            return false;
        }
        return true;
    }
    
}
