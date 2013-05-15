/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.domain;

import com.posabro.auditor.domain.AuditData;
import com.posabro.auditor.domain.Auditable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a User
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name = "WEB_USER")
public class User implements Auditable, Serializable {
    
    /**
     * The different status of the user
     */
    public enum Status{
        ENABLED,DISABLED,TEMP_PASSWORD
    }

    /**
     * The name of the user
     */
    @Id
    @Column(length = 32)
    @Size(min = 3, max = 32)
    private String name;
    
    /**
     * The password
     */
    @Column(length = 32, columnDefinition = "VARCHAR(32)", nullable = false)
    @Size(min = 3, max = 32)
    private char[] password;
    
    /**
     * Different roles that the user has assigned
     */
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
    
    /**
     * The email address
     */
    @Size(min = 3, max = 64)
    @Column(length=64, nullable=false)
    @Email
    private String email;
    
    /**
     * Indicates whether the email address has been verified or not
     */
    private boolean verifiedEmail;
    
    /**
     * The status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status;
    
    /**
     * The auditData
     */
    @Embedded
    private AuditData auditData;

    /**
     * Creates an instance of <code>User</code> class
     */
    public User() {
        this(null, null);
    }
    
    /**
     * Creates an instance of <code>User</code> class
     * 
     * @param name - the name to set
     * @param password - the password to set
     */
    public User(String name, char[] password) {
        this(name, password, new ArrayList<Role>());
    }

    /**
     * Creates an instance of <code>User</code> class
     * 
     * @param name - the name to set
     * @param password - the password to set
     * @param roles - the roles to set
     */
    public User(String name, char[] password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.auditData = new AuditData();
        this.status = Status.DISABLED;
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
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * @param password - the password to set
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email - the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the verifiedEmail
     */
    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    /**
     * @param verifiedEmail - the verifiedEmail to set
     */
    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }
    
    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status - the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
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
        return "User{" + "name=" + name + ", password=" + password + '}';
    }
}
