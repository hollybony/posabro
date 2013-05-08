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
 *
 * @author Carlos Juarez
 */
@Embeddable
public class Address implements Serializable {

    @Column(name = "ADDRESS_LINE_01", length=100, nullable=false)
    private String line1;
    
    @Column(name = "ADDRESS_LINE_02", length=100)
    private String line2;
    
    @Column(name = "ZIP_CODE", nullable=false)
    private int zipCode;
    
    @Column(name = "CITY_NAME", length=50, nullable=false)
    private String city;
    
    @ManyToOne(optional=false)
    @JoinColumns({
        @JoinColumn(name="STATE_ID", referencedColumnName="ID"),
        @JoinColumn(name="COUNTRY_ID", referencedColumnName="COUNTRY_ID")})
    private State state;

    public Address() {
        this("", "", 0, "", null);
    }

    public Address(String line1, String line2, int zipCode, String city, State state) {
        this.line1 = line1;
        this.line2 = line2;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
