/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable{
    
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name="PRODUCT_ID", length=20)
    private ProductType id;
    
    @Column(name="PRODUCT_DESC", length=50, nullable=false)
    private String description;
    
    @Column(name="BOL_DESC", length=200)
    private String bolDescription;

    public Product(){
        this(null,null,null);
    }
    
    public Product(ProductType id, String description, String bolDescription){
        this.id = id;
        this.description = description;
        this.bolDescription = bolDescription;
    }
    
    public ProductType getId() {
        return id;
    }

    public void setId(ProductType id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBolDescription() {
        return bolDescription;
    }

    public void setBolDescription(String bolDescription) {
        this.bolDescription = bolDescription;
    }
    
}
