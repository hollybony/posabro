/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a conversion factor
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="SYSTEM_CONVERSIONS")
public class ConversionFactor implements Serializable{
    
    /**
     * The primary key
     */
    @EmbeddedId
    private ConversionFactorPK conversionFactorPK;
    
    /**
     * The factor
     */
    @Column(name="CONVERSION_FACTOR", precision=8, scale=2, nullable=false)
    private BigDecimal factor;
    
    /**
     * Constructs an instance of <code>ConversionFactor</code> class
     */
    public ConversionFactor(){
        this(null,null,BigDecimal.ZERO);
    }
    
    /**
     * Constructs an instance of <code>ConversionFactor</code> class
     * 
     * @param fromUnit - fromUnit to set
     * @param toUnit - toUnit to set
     * @param factor - factor to set
     */
    public ConversionFactor(UnitOfMeasurement fromUnit, UnitOfMeasurement toUnit, BigDecimal factor){
        conversionFactorPK = new ConversionFactorPK(fromUnit, toUnit);
        this.factor = factor;
    }
    
    /**
     * Convert the value received to the equivalent value of this factor
     * 
     * @param from - value to convert
     * @return converted value
     */
    public BigDecimal convert(BigDecimal from){
        return from.multiply(factor);
    }

    /**
     * @return the conversionFactorPK
     */
    public ConversionFactorPK getConversionFactorPK() {
        return conversionFactorPK;
    }

    /**
     * @param conversionFactorPK - the conversionFactorPK to set
     */
    public void setConversionFactorPK(ConversionFactorPK conversionFactorPK) {
        this.conversionFactorPK = conversionFactorPK;
    }

    /**
     * @return the factor
     */
    public BigDecimal getFactor() {
        return factor;
    }

    /**
     * @param factor - the factor to set
     */
    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }
    
}
