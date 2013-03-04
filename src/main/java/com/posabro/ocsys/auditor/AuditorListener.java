/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.auditor;

import com.posabro.ocsys.auditor.domain.Auditable;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Carlos
 */
public class AuditorListener {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(AuditorListener.class);

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        } else {
            return "anonymous";
        }
    }

    @PrePersist
    public void touchForCreate(Object target) {
            logger.debug("touchForCreate target : " + target);
        if (target instanceof Auditable) {
            Auditable auditable = (Auditable) target;
            auditable.getAuditData().setCreatedBy(getCurrentUser());
            auditable.getAuditData().setCreatedDate(new Date());
        }
    }

    @PreUpdate
    public void touchForUpdate(Object target) {
        logger.debug("touchForUpdate target : " + target);
        if (target instanceof Auditable) {
            Auditable auditable = (Auditable) target;
            auditable.getAuditData().setModifiedBy(getCurrentUser());
            auditable.getAuditData().setModifiedDate(new Date());
        }
    }
}
