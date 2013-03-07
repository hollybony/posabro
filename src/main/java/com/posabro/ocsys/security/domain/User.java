/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import com.posabro.ocsys.auditor.domain.AuditData;
import com.posabro.ocsys.auditor.domain.Auditable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name = "WEB_USER")
public class User implements Auditable, Serializable {

    @Id
    @Column(length = 32)
    @Size(min = 3, max = 32)
    private String name;
    
    @Column(length = 32, columnDefinition = "VARCHAR(32)", nullable = false)
    @Size(min = 3, max = 32)
    private char[] password;
    
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
    
    @Size(min = 3, max = 64)
    @Email
    private String email;
    
    @Column(nullable=false)
    private boolean enabled;
    
    private boolean verifiedEmail;
    
    @Embedded
    private AuditData auditData;

    public User() {
        this(null, null, new ArrayList<Role>());
    }

    public User(String name, char[] password, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.auditData = new AuditData();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
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
        return "User{" + "name=" + name + ", password=" + password + '}';
    }
}
