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
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="CUSTOMER_FACILITIES")
public class Facility implements Serializable{
    
    @EmbeddedId
    private FacilityPK facilityPK;
    
    @Column(name="FACILITY_NAME", length=100, nullable=false)
    private String name;
    
    @Embedded
    private Address address;
    
    public Facility(){
        this(null,null,null,null,null);
    }

    public Facility(String facilityId, String customerId, String companyId, String name, Address address){
        this.facilityPK = new FacilityPK(facilityId, customerId, companyId);
        this.name = name;
        this.address = address;
    }
    
    public FacilityPK getFacilityPK() {
        return facilityPK;
    }

    public void setFacilityPK(FacilityPK facilityPK) {
        this.facilityPK = facilityPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Facility{" + "facilityPK=" + facilityPK + ", name=" + name + '}';
    }
    
}
