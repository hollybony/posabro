/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="CONTAINERS")
public class Container implements Serializable{
    
    @Id
    @Column(name="CONTAINER_ID", length=20)
    private String id;
    
    @Column(name="LTS_FULL_CAPACITY", nullable=false)
    private double ltsFillCapacity;
    
    @Column(name="LTS_FILL_CAPACITY", nullable=false)
    private double ltsFullCapacity;
    
    @Column(name="TARE_WGT", nullable=false)
    private double tareWeight;
    
    @Enumerated(EnumType.STRING)
    @Column(name="CONTAINER_TYPE", length=8, nullable=false)
    private ContainerType type;
    
    public Container(){
        this(null,0,0,0);
    }

    public Container(String id, double ltsFillCapacity, double ltsFullCapacity, double tareWeight){
        this.id = id;
        this.ltsFillCapacity = ltsFillCapacity;
        this.ltsFullCapacity = ltsFullCapacity;
        this.tareWeight = tareWeight;
        type = ContainerType.ISO;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLtsFillCapacity() {
        return ltsFillCapacity;
    }

    public void setLtsFillCapacity(double ltsFillCapacity) {
        this.ltsFillCapacity = ltsFillCapacity;
    }

    public double getLtsFullCapacity() {
        return ltsFullCapacity;
    }

    public void setLtsFullCapacity(double ltsFullCapacity) {
        this.ltsFullCapacity = ltsFullCapacity;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(double tareWeight) {
        this.tareWeight = tareWeight;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }
    
}
