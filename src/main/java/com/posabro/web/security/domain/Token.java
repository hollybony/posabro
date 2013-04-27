/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * User token
 * 
 * @author Carlos Juarez
 */
@Entity
@Table(name = "USER_KEYS")
public class Token implements Serializable {
    
    /**
     * Different types of token
     */
    public enum Type{
        EMAIL,TEMP_PASSWORD
    }
    
    /**
     * The id of the token
     */
    @Id
    @Column(name="confirmKey", length = 32)
    private String id;
    
    /**
     * The userName
     */
    @Column(length = 32)
    @Size(min = 3, max = 32)
    private String userName;
    
    /**
     * The expirationDate
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    
    /**
     * The type of token
     */
    @Enumerated(EnumType.STRING)
    @Column(name="keyType")
    private Type type;

    /**
     * Constructs an instance of <code>Token</code> class
     */
    public Token(){
        this(null,null);
    }
    
    /**
     * Constructs an instance of <code>Token</code> class
     * 
     * @param id - the id to set
     * @param userName - the userName to set
     */
    public Token(String id, String userName){
        this.id = id;
        this.userName = userName;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        this.expirationDate = cal.getTime();
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName - the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate - the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type - the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
    
}
