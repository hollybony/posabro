/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a facility
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="CUSTOMER_FACILITIES")
public class Facility implements Serializable{
    
    /**
     * The primary key
     */
    @EmbeddedId
    private FacilityPK facilityPK;
    
    /**
     * The name
     */
    @Column(name="FACILITY_NAME", length=100, nullable=false)
    private String name;
    
    /**
     * The address
     */
    @Embedded
    private Address address;
    
    /**
     * Constructs an instance of <code>Facility</code> class
     */
    public Facility(){
        this(null,null,null,null,null);
    }

    /**
     * Constructs an instance of <code>Facility</code> class
     * 
     * @param facilityId - the facilityId that will be part of the primary key
     * @param customerId - the customerId that will be part of the primary key
     * @param companyId - the companyId that will be part of the primary key
     * @param name - the name to set
     * @param address - the address to set
     */
    public Facility(String facilityId, String customerId, String companyId, String name, Address address){
        this.facilityPK = new FacilityPK(facilityId, customerId, companyId);
        this.name = name;
        this.address = address;
    }
    
    /**
     * @return the facilityPK
     */
    public FacilityPK getFacilityPK() {
        return facilityPK;
    }

    /**
     * @param facilityPK - the facilityPK to set
     */
    public void setFacilityPK(FacilityPK facilityPK) {
        this.facilityPK = facilityPK;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address - the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Facility{" + "facilityPK=" + facilityPK + ", name=" + name + '}';
    }
    
}
