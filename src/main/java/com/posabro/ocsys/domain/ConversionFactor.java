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
    
    @Column(name="CONVERSION_FACTOR", nullable=false)
    private int factor;
    
    public ConversionFactor(){
        this(null,null,0);
    }
    
    public ConversionFactor(UnitOfMeasurement fromUnit, UnitOfMeasurement toUnit, int factor){
        conversionFactorPK = new ConversionFactorPK(fromUnit, toUnit);
        this.factor = factor;
    }

    public ConversionFactorPK getConversionFactorPK() {
        return conversionFactorPK;
    }

    public void setConversionFactorPK(ConversionFactorPK conversionFactorPK) {
        this.conversionFactorPK = conversionFactorPK;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
    
}
