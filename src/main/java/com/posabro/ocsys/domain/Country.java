/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a country
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="COUNTRIES")
public class Country implements Serializable{
    
    /**
     * The id
     */
    @Id
    @Column(name="COUNTRY_ID", length=5)
    private String id;
    
    /**
     * The name
     */
    @Column(name="COUNTRY_NAME", length=100, nullable=false)
    private String name;
    
    /**
     * Constructs an instance of <code>Country</code> class
     */
    public Country(){
        this(null,null);
    }
    
    /**
     * Constructs an instance of <code>Country</code> class
     * 
     * @param id - the id to set
     * @param name - the name to set
     */
    public Country(String id, String name){
        this.id = id;
        this.name = name;
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
    
}
