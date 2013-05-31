/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a customer
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="CUSTOMERS")
public class Customer implements Serializable{
    
    /**
     * The primary key
     */
    @EmbeddedId
    private CustomerPK customerPK;
    
    /**
     * The name
     */
    @Column(name="CUST_NAME", length=100, nullable=false)
    private String name;
    
    /**
     * The taxId
     */
    @Column(name="TAX_ID", length=20, nullable=false)
    private String taxId;
    
    /**
     * The company
     */
    @MapsId("companyId")
    @ManyToOne
    @JoinColumn(name="COMPANY_ID", columnDefinition="VARCHAR(20)", insertable = false, updatable = false)
    private Company company;
    
    /**
     * The address
     */
    @Embedded
    private Address address;
    
    /**
     * The facilities
     */
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="CUST_ID", referencedColumnName="CUST_ID"),
        @JoinColumn(name="COMPANY_ID", referencedColumnName="COMPANY_ID")})
    private List<Facility> facilities;
    
    /**
     * Constructs an instance of <code>Customer</code> class
     */
    public Customer(){
        this(null,null,null,null);
    }
    
    /**
     * Constructs an instance of <code>Customer</code> class
     * 
     * @param customerId - the customerId that will be part of the primary key
     * @param name - the name to set
     * @param taxId - the taxId to set
     * @param company - the company to set and whose id will be part of the primary key
     */
    public Customer(String customerId, String name, String taxId, Company company){
        this(customerId, name, taxId, company, new Address());
    }
    
    /**
     * Constructs an instance of <code>Customer</code> class
     * 
     * @param customerId - the customerId that will be part of the primary key
     * @param name - the name to set
     * @param taxId - the taxId to set
     * @param company - the company to set and whose id will be part of the primary key
     * @param address - the address to set
     */
    public Customer(String customerId, String name, String taxId, Company company, Address address){
        this.customerPK = new CustomerPK(customerId, company==null?null:company.getId());
        this.name = name;
        this.taxId = taxId;
        this.company = company;
        this. address = address;
        facilities = new ArrayList<Facility>();
    }

    /**
     * Add a new facility
     * 
     * @param facility - the facility to add
     * @return this to continue adding facilities or whatever
     */
    public Customer addFacility(Facility facility){
        facilities.add(facility);
        return this;
    }
    
    /**
     * @return the customerPK
     */
    public CustomerPK getCustomerPK() {
        return customerPK;
    }

    /**
     * @param customerPK - the customerPK to set
     */
    public void setCustomerPK(CustomerPK customerPK) {
        this.customerPK = customerPK;
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

    /**
     * @return the facilities
     */
    public List<Facility> getFacilities() {
        return facilities;
    }

    /**
     * @param facilities - the facilities to set
     */
    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }
    
    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company -  the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

}
