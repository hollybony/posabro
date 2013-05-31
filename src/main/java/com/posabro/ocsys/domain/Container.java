/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a container
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name="CONTAINERS")
public class Container implements Serializable{
    
    /**
     * The id
     */
    @Id
    @Column(name="CONTAINER_ID", length=20)
    private String id;
    
    /**
     * The liters fill capacity
     */
    @Column(name="LTS_FILL_CAPACITY", precision=8, scale=2, nullable=false)
    private BigDecimal ltsFillCapacity;
    
    /**
     * The liters full capacity
     */
    @Column(name="LTS_FULL_CAPACITY", precision=8, scale=2, nullable=false)
    private BigDecimal ltsFullCapacity;
    
    /**
     * The tare weight
     */
    @Column(name="TARE_WGT", precision=8, scale=2, nullable=false)
    private BigDecimal tareWeight;
    
    /**
     * The type
     */
    @Enumerated(EnumType.STRING)
    @Column(name="CONTAINER_TYPE", length=8, nullable=false)
    private ContainerType type;
    
    /**
     * Constructs an instance of <code>Container</code> class
     */
    public Container(){
        this(null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     * Constructs an instance of <code>Container</code> class
     * 
     * @param id - the id to set
     * @param ltsFillCapacity - the ltsFillCapacity to set
     * @param ltsFullCapacity - the ltsFullCapacity to set
     * @param tareWeight - the tareWeight to set
     */
    public Container(String id, BigDecimal ltsFillCapacity, BigDecimal ltsFullCapacity, BigDecimal tareWeight){
        this.id = id;
        this.ltsFillCapacity = ltsFillCapacity;
        this.ltsFullCapacity = ltsFullCapacity;
        this.tareWeight = tareWeight;
        type = ContainerType.ISO;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ltsFillCapacity
     */
    public BigDecimal getLtsFillCapacity() {
        return ltsFillCapacity;
    }

    /**
     * @param ltsFillCapacity - the ltsFillCapacity to set
     */
    public void setLtsFillCapacity(BigDecimal ltsFillCapacity) {
        this.ltsFillCapacity = ltsFillCapacity;
    }

    /**
     * @return the ltsFullCapacity
     */
    public BigDecimal getLtsFullCapacity() {
        return ltsFullCapacity;
    }

    /**
     * @param ltsFullCapacity - the ltsFullCapacity to set
     */
    public void setLtsFullCapacity(BigDecimal ltsFullCapacity) {
        this.ltsFullCapacity = ltsFullCapacity;
    }

    /**
     * @return the tareWeight
     */
    public BigDecimal getTareWeight() {
        return tareWeight;
    }

    /**
     * @param tareWeight - the tareWeight to set
     */
    public void setTareWeight(BigDecimal tareWeight) {
        this.tareWeight = tareWeight;
    }

    /**
     * @return the type
     */
    public ContainerType getType() {
        return type;
    }

    /**
     * @param type - the type to set
     */
    public void setType(ContainerType type) {
        this.type = type;
    }
    
}
