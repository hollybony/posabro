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
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="CUSTOMERS")
public class Customer implements Serializable{
    
    @EmbeddedId
    private CustomerPK customerPK;
    
    @Column(name="CUST_NAME", length=100, nullable=false)
    private String name;
    
    @Column(name="TAX_ID", length=20, nullable=false)
    private String taxId;
    
    @MapsId("companyId")
    @ManyToOne
    @JoinColumn(name="COMPANY_ID", columnDefinition="VARCHAR(20)", insertable = false, updatable = false)
    private Company company;
    
    @Embedded
    private Address address;
    
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="CUST_ID", referencedColumnName="CUST_ID"),
        @JoinColumn(name="COMPANY_ID", referencedColumnName="COMPANY_ID")})
    private List<Facility> facilities;
    
    public Customer(){
        this(null,null,null,null);
    }
    
    public Customer(String customerId, String name, String taxId, Company company){
        this(customerId, name, taxId, company, new Address());
    }
    
    public Customer(String customerId, String name, String taxId, Company company, Address address){
        this.customerPK = new CustomerPK(customerId, company==null?null:company.getId());
        this.name = name;
        this.taxId = taxId;
        this.company = company;
        this. address = address;
        facilities = new ArrayList<Facility>();
    }

    public Customer addFacility(Facility facility){
        facilities.add(facility);
        return this;
    }
    
    public CustomerPK getCustomerPK() {
        return customerPK;
    }

    public void setCustomerPK(CustomerPK customerPK) {
        this.customerPK = customerPK;
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

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }
    
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
