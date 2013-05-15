/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Carlos Juarez
 */
@Embeddable
public class Content implements Serializable {

    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_GALS", precision=8, scale=2)
    private BigDecimal containedGallons;
    
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_KGS", precision=8, scale=2, nullable=false)
    private BigDecimal containedKgs;
    
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_LBS", precision=8, scale=2, nullable=false)
    private BigDecimal containedLbs;
    
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_LTS", precision=8, scale=2)
    private BigDecimal containedLts;
    
    public Content(){

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
