/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="WEB_USER")
public class User implements Serializable {
    
    @Id
    @Column(length=32)
    @Size(min=3,max=32)
    private String name;
    
    @Column(length=32, columnDefinition="VARCHAR(32)", nullable=false)
    @Size(min=3,max=32)
    private char[] password;
    
    @NotEmpty
    @ManyToMany(fetch= FetchType.LAZY)
    private List<Role> roles;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date creationDate;
    
    public User(){
        this(null,null,new ArrayList<Role>(),null);
    }
    
    public User(String name, char[] password, List<Role> roles, Date creationDate){
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.creationDate = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", password=" + password + ", creationDate=" + creationDate + '}';
    }
    
}
