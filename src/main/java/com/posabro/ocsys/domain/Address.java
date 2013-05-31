/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Model of value object that represents an address
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class Address implements Serializable {

    /**
     * The first line of the address
     */
    @Column(name = "ADDRESS_LINE_01", length=100, nullable=false)
    private String line1;
    
    /**
     * The second line of the address
     */
    @Column(name = "ADDRESS_LINE_02", length=100)
    private String line2;
    
    /**
     * The zip code
     */
    @Column(name = "ZIP_CODE", nullable=false)
    private int zipCode;
    
    /**
     * The city
     */
    @Column(name = "CITY_NAME", length=50, nullable=false)
    private String city;
    
    /**
     * The state or province
     */
    @ManyToOne(optional=false)
    @JoinColumns({
        @JoinColumn(name="STATE_ID", referencedColumnName="STATE_ID"),
        @JoinColumn(name="COUNTRY_ID", referencedColumnName="COUNTRY_ID")})
    private State state;

    /**
     * Creates an instance of <code>Address</code> class
     */
    public Address() {
        this("", "", 0, "", null);
    }

    /**
     * * Creates an instance of <code>Address</code> class
     * 
     * @param line1 - the line1 to set
     * @param line2 - the line2 to set
     * @param zipCode - the zipCode to set
     * @param city - the city to set
     * @param state - the state to set
     */
    public Address(String line1, String line2, int zipCode, String city, State state) {
        this.line1 = line1;
        this.line2 = line2;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }
    
    /**
     * @return the line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * @param line1 - the line1 to set
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * @return the line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * @param line2 - the line2 to set
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * @return the zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode - the zipCode to set
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city - the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state - the state to set
     */
    public void setState(State state) {
        this.state = state;
    }
}
