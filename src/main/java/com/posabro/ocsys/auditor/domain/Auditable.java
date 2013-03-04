/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.auditor.domain;

import com.posabro.ocsys.auditor.domain.AuditData;

/**
 *
 * @author Carlos
 */
public interface Auditable {

    public AuditData getAuditData();
    
}
