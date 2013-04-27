/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.auditor.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Audit data ready to be used by entities that need to keep tracking about their changes
 * 
 * @author Carlos Juarez
 */
@Embeddable
public class AuditData implements Serializable{
    
    /**
     * The created date
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date createdDate;
    
    /**
     * User who created the entity
     */
    @Column(length=32,nullable=false)
    private String createdBy;
    
    /**
     * The last modification date
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    /**
     * User who made the last modification
     */
    @Column(length=32)
    private String modifiedBy;
   
    /**
     * Creates an instance of <code>AuditData</code> class
     */
    public AuditData(){
        this(null,null,null,null);
    }
    
    /**
     * Creates an instance of <code>AuditData</code> class
     * 
     * @param createdDate - the createdDate to set
     * @param createdBy - the createdBy to set
     * @param modifiedDate - the modifiedDate to set
     * @param modifiedBy - the modifiedBy to set
     */
    public AuditData(Date createdDate, String createdBy, Date modifiedDate, String modifiedBy){
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate - the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy - the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate - the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy - the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
}
