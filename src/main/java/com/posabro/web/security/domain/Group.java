/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.domain;

import com.posabro.auditor.domain.AuditData;
import com.posabro.auditor.domain.Auditable;
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
 * Represents a group
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name = "WEB_GROUP")
public class Group implements Auditable, Serializable {

    /**
     * The id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    
    /**
     * The name
     */
    @Size(min = 6, max = 64)
    private String name;
    
    /**
     * The roles which this group contains
     */
    @ManyToMany
    private List<Role> roles;
    
    /**
     * The users that this group contains
     */
    @ManyToMany
    private List<User> members;
    
    /**
     * The auditData
     */
    @Embedded
    private AuditData auditData;
    
    /**
     * Creates an instance of <code>Group</code> class
     */
    public Group(){
        this.auditData = new AuditData();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles - the role to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return the members
     */
    public List<User> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }

    /**
     * @return the auditData
     */
    @Override
    public AuditData getAuditData() {
        return auditData;
    }

    /**
     * @param auditData - the auditData to set
     */
    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }

    @Override
    public String toString() {
        return "Group{" + "id=" + id + ", name=" + name + ", roles=" + roles + ", members=" + members + '}';
    }
    
}
