/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary key of <code>State</code>
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class StatePK implements Serializable{
    
    /**
     * The id
     */
    @Column(name="STATE_ID", length=5)
    private String id;
    
    /**
     * The countryId
     */
    @Column(name="COUNTRY_ID", length=5)
    private String countryId;

    /**
     * Creates an instance of <code>StatePK</code> class
     */
    public StatePK(){
        this(null,null);
    }
    
    /**
     * Creates an instance of <code>StatePK</code> class
     * 
     * @param id - the id to set
     * @param countryId - the countryId to set
     */
    public StatePK(String id, String countryId){
        this.id = id;
        this.countryId = countryId;
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
     * @return the countryId
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * @param countryId - the countryId to set
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.countryId != null ? this.countryId.hashCode() : 0);
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
        final StatePK other = (StatePK) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
