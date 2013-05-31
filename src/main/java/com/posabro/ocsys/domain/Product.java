/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Represents a product
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable{
    
    /**
     * The id
     */
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name="PRODUCT_ID", length=20)
    private ProductType id;
    
    /**
     * The description
     */
    @Column(name="PRODUCT_DESC", length=50, nullable=false)
    private String description;
    
    /**
     * The bolDescription
     */
    @Column(name="BOL_DESC", length=200)
    private String bolDescription;

    /**
     * The nacnPct
     */
    @Transient
    private BigDecimal nacnPct;
    
    /**
     * The specificGravity
     */
    @Transient
    private BigDecimal specificGravity;
    
    /**
     * The ph
     */
    @Transient
    private BigDecimal ph;
    
    /**
     * Constructs an instance of <code>Product</code> class
     */
    public Product(){
        this(null,null,null);
    }
    
    /**
     * Constructs an instance of <code>Product</code> class
     * 
     * @param id - the id to set
     * @param description - the description to set
     * @param bolDescription - the bolDescription to set
     */
    public Product(ProductType id, String description, String bolDescription){
        this.id = id;
        this.description = description;
        this.bolDescription = bolDescription;
        this.nacnPct = BigDecimal.ZERO;
        this.specificGravity = BigDecimal.ZERO;
        this.ph = BigDecimal.ZERO;
    }
    
    /**
     * @return the id
     */
    public ProductType getId() {
        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(ProductType id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description - the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the bolDescription
     */
    public String getBolDescription() {
        return bolDescription;
    }

    /**
     * @param bolDescription - the bolDescription to set
     */
    public void setBolDescription(String bolDescription) {
        this.bolDescription = bolDescription;
    }

    /**
     * @return the nacnPct
     */
    public BigDecimal getNacnPct() {
        return nacnPct;
    }

    /**
     * @param nacnPct - the nacnPct to set
     */
    public void setNacnPct(BigDecimal nacnPct) {
        this.nacnPct = nacnPct;
    }

    /**
     * @return the specificGravity
     */
    public BigDecimal getSpecificGravity() {
        return specificGravity;
    }

    /**
     * @param specificGravity - the specificGravity to set
     */
    public void setSpecificGravity(BigDecimal specificGravity) {
        this.specificGravity = specificGravity;
    }

    /**
     * @return the ph
     */
    public BigDecimal getPh() {
        return ph;
    }

    /**
     * @param ph - the ph to set
     */
    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }
    
}
