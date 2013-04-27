/**
 * Created on Oct 18, 2011
 */
package com.posabro.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * This class is aware of the audit
 * 
 * @author Carlos Juarez
 *
 */
public class AuditorAwareBean implements AuditorAware<String> {

    /**
     * Every time that the Spring audit listener requires the current auditor it calls this method
     * 
     * @return current auditor taken from the user logged or a default user name
     */
    @Override
    public String getCurrentAuditor() {
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
