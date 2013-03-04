/**
 * Created on Oct 18, 2011
 */
package com.posabro.ocsys.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Clarence
 *
 */
public class AuditorAwareBean implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        AuditingEntityListener a;
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser;
        if (authentication != null) {
            currentUser = authentication.getName();
        } else {
            currentUser = "anonymous";
        }
        return currentUser;
    }
}
