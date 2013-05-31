/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a carrier
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="CARRIERS")
public class Carrier implements Serializable{
 
    /**
     * The id
     */
    @Id
    @Column(name="CARRIER_ID", length=20)
    private String id;
    
    /**
     * The name
     */
    @Column(name="CARRIER_NAME", length=100, nullable=false)
    private String name;
    
    /**
     * The taxIs
     */
    @Column(name="TAX_ID", length=20, nullable=false)
    private String taxId;
    
    /**
     * The address
     */
    @Embedded
    private Address address;
    
    /**
     * Constructs an instance of <code>Carrier</code> class
     */
    public Carrier(){
        this(null,null,null,new Address());
    }
    
    /**
     * Constructs an instance of <code>Carrier</code> class
     * 
     * @param id - the id to set
     * @param name - the name to set
     * @param taxId - the taxId to set
     * @param address - the address to set
     */
    public Carrier(String id, String name, String taxId, Address address){
        this.id = id;
        this.name = name;
        this.taxId = taxId;
        this.address = address;
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
     * @return the taxId
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * @param taxId - the taxId to set
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
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
    
}
