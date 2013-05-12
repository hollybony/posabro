/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 *
 * @author Carlos Juarez
 */
@Embeddable
public class Content implements Serializable {

    @Column(name="CONTAINED_GALS")
    private double containedGallons;
    
    @Column(name="CONTAINED_KGS", nullable=false)
    private double containedKgs;
    
    @Column(name="CONTAINED_LBS", nullable=false)
    private double containedLbs;
    
    @Column(name="CONTAINED_LTS")
    private double containedLts;
    
    
    public double getContainedGallons() {
        return containedGallons;
    }

    public void setContainedGallons(double containedGallons) {
        this.containedGallons = containedGallons;
    }

    public double getContainedKgs() {
        return containedKgs;
    }

    public void setContainedKgs(double containedKgs) {
        this.containedKgs = containedKgs;
    }

    public double getContainedLbs() {
        return containedLbs;
    }

    public void setContainedLbs(double containedLbs) {
        this.containedLbs = containedLbs;
    }

    public double getContainedLts() {
        return containedLts;
    }

    public void setContainedLts(double containedLts) {
        this.containedLts = containedLts;
    }
}
