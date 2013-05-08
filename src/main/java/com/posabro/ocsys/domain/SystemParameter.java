/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos Juarez
 */
@Entity
@Table(name="SYSTEM_CONTROL_INFO")
public class SystemParameter implements Serializable{
    
    @EmbeddedId
    private SystemParameterId systemParameterId;
    
    @Column(name="CURRENT_YEAR", nullable=false)
    private int currentYear;
    
    @Column(name="LAST_BOL_SEQ", nullable=false)
    private int lastBolConsecituve;
    
    @OneToOne
    @JoinColumns({
        @JoinColumn(name="COMPANY_ID", referencedColumnName="COMPANY_ID"),
        @JoinColumn(name="BRANCH_ID", referencedColumnName="ID")})
    private Branch branch;
    
    public SystemParameter(){
        this(0, 0, null);
    }
    
    public SystemParameter(int currentYear, int lastBolConsecituve, Branch branch){
        systemParameterId = new SystemParameterId(branch==null?null:branch.getBranchId().getCompanyId(),branch==null?null:branch.getBranchId().getId());
    }

    public SystemParameterId getSystemParameterId() {
        return systemParameterId;
    }

    public void setSystemParameterId(SystemParameterId systemParameterId) {
        this.systemParameterId = systemParameterId;
    }
  
    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getLastBolConsecituve() {
        return lastBolConsecituve;
    }

    public void setLastBolConsecituve(int lastBolConsecituve) {
        this.lastBolConsecituve = lastBolConsecituve;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
}
