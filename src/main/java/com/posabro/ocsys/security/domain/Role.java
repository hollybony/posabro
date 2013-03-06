/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import com.posabro.ocsys.auditor.domain.AuditData;
import com.posabro.ocsys.auditor.domain.Auditable;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name = "WEB_ROLE")
public class Role implements Auditable, Serializable {

    @Id
    @Column(length = 32)
    @Size(min = 3, max = 32)
    private String name;
    
    @Column(length = 64)
    @Size(min = 6, max = 64)
    private String description;
    
    @Embedded
    private AuditData auditData;
    
    public Role(){
        this.auditData = new AuditData();
    }

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

    @Override
    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + ", description=" + description + '}';
    }
    
}
