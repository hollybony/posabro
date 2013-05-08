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
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="CARRIERS")
public class Carrier implements Serializable{
 
    @Id
    @Column(name="ID", length=20)
    private String id;
    
    @Column(name="CARRIER_NAME", length=100, nullable=false)
    private String name;
    
    @Column(name="TAX_ID", length=20, nullable=false)
    private String taxId;
    
    @Embedded
    private Address address;
    
    public Carrier(){
        this(null,null,null,new Address());
    }
    
    public Carrier(String id, String name, String taxId, Address address){
        this.id = id;
        this.name = name;
        this.taxId = taxId;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
}
