/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "WEB_EMAIL_KEYS")
public class EmailConfirmationKey implements Serializable {
    
    @Id
    @Column(name="confirmKey", length = 32)
    private String key;
    
    @Column(length = 32)
    @Size(min = 3, max = 32)
    private String userName;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    public EmailConfirmationKey(){
        this(null,null);
    }
    
    public EmailConfirmationKey(String key, String userName){
        this.key = key;
        this.userName = userName;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        this.expirationDate = cal.getTime();
    }
    
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
}