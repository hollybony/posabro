/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="WEB_ROLE")
public class Role implements Serializable {
    
    @Id
    @Column(length=32)
    @Size(min=3,max=32)
    private String name;
    
    
    @Column(length=64)
    @Size(min=6,max=64)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
