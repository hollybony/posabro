/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;


/**
 *
 * @author Carlos Juarez
 */
@Embeddable
public class Content implements Serializable {

    @Column(name="CONTAINED_GALS", precision=8, scale=2)
    private BigDecimal containedGallons;
    
    @Column(name="CONTAINED_KGS", precision=8, scale=2, nullable=false)
    private BigDecimal containedKgs;
    
    @Column(name="CONTAINED_LBS", precision=8, scale=2, nullable=false)
    private BigDecimal containedLbs;
    
    @Column(name="CONTAINED_LTS", precision=8, scale=2)
    private BigDecimal containedLts;
    
    public Content(){
        containedGallons = BigDecimal.ZERO;
        containedKgs = BigDecimal.ZERO;
        containedLbs = BigDecimal.ZERO;
        containedLts = BigDecimal.ZERO;
    }
    
    public BigDecimal getContainedGallons() {
        return containedGallons;
    }

    public void setContainedGallons(BigDecimal containedGallons) {
        this.containedGallons = containedGallons;
    }

    public BigDecimal getContainedKgs() {
        return containedKgs;
    }

    public void setContainedKgs(BigDecimal containedKgs) {
        this.containedKgs = containedKgs;
    }

    public BigDecimal getContainedLbs() {
        return containedLbs;
    }

    public void setContainedLbs(BigDecimal containedLbs) {
        this.containedLbs = containedLbs;
    }

    public BigDecimal getContainedLts() {
        return containedLts;
    }

    public void setContainedLts(BigDecimal containedLts) {
        this.containedLts = containedLts;
    }
}
