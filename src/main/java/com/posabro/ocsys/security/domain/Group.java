/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import com.posabro.ocsys.auditor.domain.AuditData;
import com.posabro.ocsys.auditor.domain.Auditable;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name = "WEB_GROUP")
public class Group implements Auditable, Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    
    @Size(min = 6, max = 64)
    private String name;
    
    @ManyToMany
    private List<Role> roles;
    
    @ManyToMany
    private List<User> members;
    
    @Embedded
    private AuditData auditData;
    
    public Group(){
        this.auditData = new AuditData();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
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
        return "Group{" + "id=" + id + ", name=" + name + ", roles=" + roles + ", members=" + members + '}';
    }
    
}
