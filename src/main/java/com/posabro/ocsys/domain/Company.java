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
 * Represents a company
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="COMPANIES")
public class Company implements Serializable{
    
    /**
     * The id
     */
    @Id
    @Column(name="COMPANY_ID", length=20)
    private String id;
    
    /**
     * The name
     */
    @Column(name="COMPANY_NAME", length=100)
    private String name;
    
    /**
     * Constructs an instance of <code>Company</code> class
     */
    public Company(){
        this(null,null);
    }
    
    /**
     * Constructs an instance of <code>Company</code> class
     * 
     * @param id - the id to set
     * @param name - the name to set
     */
    public Company(String id, String name){
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

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + '}';
    }
    
}
