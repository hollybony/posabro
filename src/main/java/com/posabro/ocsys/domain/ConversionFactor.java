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
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="SYSTEM_CONVERSIONS")
public class ConversionFactor implements Serializable{
    
    @EmbeddedId
    private ConversionFactorPK conversionFactorPK;
    
    @Column(name="CONVERSION_FACTOR", precision=8, scale=2, nullable=false)
    private BigDecimal factor;
    
    public ConversionFactor(){
        this(null,null,BigDecimal.ZERO);
    }
    
    public ConversionFactor(UnitOfMeasurement fromUnit, UnitOfMeasurement toUnit, BigDecimal factor){
        conversionFactorPK = new ConversionFactorPK(fromUnit, toUnit);
        this.factor = factor;
    }
    
    public BigDecimal convert(BigDecimal from){
        return from.multiply(factor);
    }

    public ConversionFactorPK getConversionFactorPK() {
        return conversionFactorPK;
    }

    public void setConversionFactorPK(ConversionFactorPK conversionFactorPK) {
        this.conversionFactorPK = conversionFactorPK;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }
    
}
