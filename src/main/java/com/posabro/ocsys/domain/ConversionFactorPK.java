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
 * Primary key of <code>ConversionFactor</code>
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class ConversionFactorPK implements Serializable{
    
    /**
     * The fromUnit
     */
    @Enumerated(EnumType.STRING)
    @Column(name="MU_FROM", length=10)
    private UnitOfMeasurement fromUnit;
    
    /**
     * The toUnit
     */
    @Enumerated(EnumType.STRING)
    @Column(name="MU_TO", length=10)
    private UnitOfMeasurement toUnit;
    
    /**
     * Constructs an instance of <code>ConversionFactorPK</code> class
     */
    public ConversionFactorPK(){
        this(null, null);
    }
    
    /**
     * * Constructs an instance of <code>ConversionFactorPK</code> class
     * 
     * @param fromUnit - fromUnit to set
     * @param toUnit - toUnit to set
     */
    public ConversionFactorPK(UnitOfMeasurement fromUnit, UnitOfMeasurement toUnit){
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    /**
     * @return the fromUnit
     */
    public UnitOfMeasurement getFromUnit() {
        return fromUnit;
    }

    /**
     * @param fromUnit - the fromUnit to set
     */
    public void setFromUnit(UnitOfMeasurement fromUnit) {
        this.fromUnit = fromUnit;
    }

    /**
     * @return the toUnit
     */
    public UnitOfMeasurement getToUnit() {
        return toUnit;
    }

    /**
     * @param toUnit - the toUnit to set
     */
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
        final ConversionFactorPK other = (ConversionFactorPK) obj;
        if (this.fromUnit != other.fromUnit) {
            return false;
        }
        if (this.toUnit != other.toUnit) {
            return false;
        }
        return true;
    }
    
}
