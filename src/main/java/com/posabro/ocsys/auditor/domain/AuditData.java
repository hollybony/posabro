/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.auditor.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Carlos
 */
@Embeddable
public class AuditData implements Serializable{
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date createdDate;
    
    @Column(length=32,nullable=false)
    private String createdBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(length=32)
    private String modifiedBy;
   
    public AuditData(){
        this(null,null,null,null);
    }
    
    public AuditData(Date createdDate, String createdBy, Date modifiedDate, String modifiedBy){
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
}
