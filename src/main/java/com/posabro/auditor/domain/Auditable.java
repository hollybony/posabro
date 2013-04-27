/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.auditor.domain;

/**
 * Interface to be implements by any entity that needs to audit
 * 
 * @author Carlos Juarez
 */
public interface Auditable {

    /**
     * @return the auditData
     */
    public AuditData getAuditData();
    
}
