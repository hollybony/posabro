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
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="COUNTRIES")
public class Country implements Serializable{
    
    @Id
    @Column(name="ID", length=5)
    private String id;
    
    @Column(name="COUNTRY_NAME", length=100, nullable=false)
    private String name;
    
    public Country(){
        this(null,null);
    }
    
    public Country(String id, String name){
        this.id = id;
        this.name = name;
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
    
}
