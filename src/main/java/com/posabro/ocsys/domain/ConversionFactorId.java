/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Carlos Juarez
 */
@Embeddable
public class ConversionFactorId implements Serializable{
    
    @Enumerated(EnumType.STRING)
    @Column(name="MU_FROM", length=10)
    private UnitOfMeasurement fromUnit;
    
    @Enumerated(EnumType.STRING)
    @Column(name="MU_TO", length=10)
    private UnitOfMeasurement toUnit;
    
    public ConversionFactorId(){
        this(null, null);
    }
    
    public ConversionFactorId(UnitOfMeasurement fromUnit, UnitOfMeasurement toUnit){
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    public UnitOfMeasurement getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(UnitOfMeasurement fromUnit) {
        this.fromUnit = fromUnit;
    }

    public UnitOfMeasurement getToUnit() {
        return toUnit;
    }

    public void setToUnit(UnitOfMeasurement toUnit) {
        this.toUnit = toUnit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.fromUnit != null ? this.fromUnit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConversionFactorId other = (ConversionFactorId) obj;
        if (this.fromUnit != other.fromUnit) {
            return false;
        }
        if (this.toUnit != other.toUnit) {
            return false;
        }
        return true;
    }
    
}
