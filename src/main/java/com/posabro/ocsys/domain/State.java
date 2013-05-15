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
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="STATES")
public class State implements Serializable {
    
    @EmbeddedId
    private StatePK statePK;
    
    @Column(name="STATE_NAME", length=100, nullable=false)
    private String name;
    
    @MapsId("countryId")
    @ManyToOne
    @JoinColumn(name="COUNTRY_ID", columnDefinition="VARCHAR(5)", insertable=false, updatable=false)
    private Country country;

    public State(){
        this(null,null,null);
    }
    
    public State(String stateId, String name, Country country){
        this.statePK = new StatePK(stateId, country==null?null:country.getId());
        this.name = name;
        this.country = country;
    }
    
    public StatePK getStatePK() {
        return statePK;
    }

    public void setStatePK(StatePK statePK) {
        this.statePK = statePK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
}
