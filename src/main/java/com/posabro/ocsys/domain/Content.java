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


/**
 * Represents the content of a bol
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class Content implements Serializable {
    
    /**
     * Creates an instance of <code>Content</code> class
     */
    public Content(){
        this(null, null, null, null);
    }
    
    /**
     * Creates an instance of <code>Content</code> class
     * 
     * @param containedGallons - the containedGallons to set
     * @param containedKgs - the containedKgs to set
     * @param containedLbs - the containedLbs to set
     * @param containedLts - the containedLts to set
     */
    public Content(BigDecimal containedGallons, BigDecimal containedKgs, BigDecimal containedLbs, BigDecimal containedLts){
        this.containedGallons = containedGallons;
        this.containedKgs = containedKgs;
        this.containedLbs = containedLbs;
        this.containedLts = containedLts;
    }

    /**
     * The containedGallons
     */
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_GALS", precision=8, scale=2)
    private BigDecimal containedGallons;
    
    /**
     * The containedKgs
     */
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_KGS", precision=8, scale=2, nullable=false)
    private BigDecimal containedKgs;
    
    /**
     * The containedLbs
     */
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_LBS", precision=8, scale=2, nullable=false)
    private BigDecimal containedLbs;
    
    /**
     * The containedLts
     */
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Column(name="CONTAINED_LTS", precision=8, scale=2)
    private BigDecimal containedLts;
    
    /**
     * @return the containedGallons
     */
    public BigDecimal getContainedGallons() {
        return containedGallons;
    }

    /**
     * @param containedGallons - the containedGallons to set
     */
    public void setContainedGallons(BigDecimal containedGallons) {
        this.containedGallons = containedGallons;
    }

    /**
     * @return the containedKgs
     */
    public BigDecimal getContainedKgs() {
        return containedKgs;
    }

    /**
     * @param containedKgs - the containedKgs to set
     */
    public void setContainedKgs(BigDecimal containedKgs) {
        this.containedKgs = containedKgs;
    }

    /**
     * @return the containedLbs
     */
    public BigDecimal getContainedLbs() {
        return containedLbs;
    }

    /**
     * @param containedLbs - the containedLbs to set
     */
    public void setContainedLbs(BigDecimal containedLbs) {
        this.containedLbs = containedLbs;
    }

    /**
     * @return the containedLts
     */
    public BigDecimal getContainedLts() {
        return containedLts;
    }

    /**
     * @param containedLts - the containedLts to set
     */
    public void setContainedLts(BigDecimal containedLts) {
        this.containedLts = containedLts;
    }
}
