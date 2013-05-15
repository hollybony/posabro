/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.domain;

import com.posabro.auditor.domain.AuditData;
import com.posabro.auditor.domain.Auditable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Represents a Role
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name = "WEB_ROLE")
public class Role implements Auditable, Serializable {

    /**
     * The name of the role
     */
    @Id
    @Column(length = 32)
    @Size(min = 3, max = 32)
    private String name;
    
    /**
     * The description
     */
    @Column(length = 64)
    @Size(min = 6, max = 64)
    private String description;
    
    /**
     * The auditData
     */
    @Embedded
    private AuditData auditData;
    
    /**
     * Creates an instance of <code>Role</code> class
     */
    public Role(){
        this.auditData = new AuditData();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description - the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the auditData
     */
    @Override
    public AuditData getAuditData() {
        return auditData;
    }

    /**
     * @param auditData - the auditData
     */
    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + ", description=" + description + '}';
    }
    
}
