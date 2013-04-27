/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.auditor;

import com.posabro.auditor.domain.Auditable;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * This auditor acts as a JPA listener so it should be registered on orm.xml file. The function of this listener
 * is to update audit properties every time that a <code>Auditable</code> is updated
 * 
 * @author Carlos Juarez
 */
public class AuditorListener {
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(AuditorListener.class);

    /**
     * Gets the current authenticated user or a default user name
     * 
     * @return the current user
     */
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        } else {
            return "anonymous";
        }
    }

    /**
     * Every time that an <code>Auditable</code> entity is persisted, this method is called and
     * the creating method of the entity are updated
     * 
     * @param target - the entity to persist
     */
    @PrePersist
    public void touchForCreate(Object target) {
            logger.debug("touchForCreate target : " + target);
        if (target instanceof Auditable) {
            Auditable auditable = (Auditable) target;
            auditable.getAuditData().setCreatedBy(getCurrentUser());
            auditable.getAuditData().setCreatedDate(new Date());
        }
    }

    /**
     * Every time that an <code>Auditable</code> entity is updated, this method is called and
     * the updating method of the entity are updated
     * 
     * @param target - the entity to update
     */
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
