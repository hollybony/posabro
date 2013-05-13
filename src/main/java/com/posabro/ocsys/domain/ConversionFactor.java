/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
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
    private double factor;
    
    public ConversionFactor(){
        this(null,null,0);
    }
    
    public ConversionFactor(UnitOfMeasurement fromUnit, UnitOfMeasurement toUnit, double factor){
        conversionFactorPK = new ConversionFactorPK(fromUnit, toUnit);
        this.factor = factor;
    }
    
    public double convert(double from){
        return from * factor;
    }

    public ConversionFactorPK getConversionFactorPK() {
        return conversionFactorPK;
    }

    public void setConversionFactorPK(ConversionFactorPK conversionFactorPK) {
        this.conversionFactorPK = conversionFactorPK;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
    
}
