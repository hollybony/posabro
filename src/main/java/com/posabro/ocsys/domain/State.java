/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * Represents a state
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="STATES")
public class State implements Serializable {
    
    /**
     * The primary key
     */
    @EmbeddedId
    private StatePK statePK;
    
    /**
     * The name
     */
    @Column(name="STATE_NAME", length=100, nullable=false)
    private String name;
    
    /**
     * The country
     */
    @MapsId("countryId")
    @ManyToOne
    @JoinColumn(name="COUNTRY_ID", columnDefinition="VARCHAR(5)", insertable=false, updatable=false)
    private Country country;

    /**
     * Constructs an instance of <code>State</code> class
     */
    public State(){
        this(null,null,null);
    }
    
    /**
     * Constructs an instance of <code>State</code> class
     * 
     * @param stateId - the stateId to set
     * @param name - the name to set
     * @param country - the country to set
     */
    public State(String stateId, String name, Country country){
        this.statePK = new StatePK(stateId, country==null?null:country.getId());
        this.name = name;
        this.country = country;
    }
    
    /**
     * @return the statePK
     */
    public StatePK getStatePK() {
        return statePK;
    }

    /**
     * @param statePK - the statePK to set
     */
    public void setStatePK(StatePK statePK) {
        this.statePK = statePK;
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
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country - the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }
    
}
