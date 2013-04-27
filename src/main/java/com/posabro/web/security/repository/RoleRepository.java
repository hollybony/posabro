/**
 * Created on Dec 13, 2011
 */
package com.posabro.web.security.repository;

import com.posabro.web.security.domain.Role;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Contains all the repository methods related to <code>Role</code>s
 * 
 * @author Carlos Juarez
 */
public interface RoleRepository extends CrudRepository<Role, String> {
    
    /**
     * Finds roles that match with the following criteria
     * 
     * @param name - name parameter which is contained in role name
     * @param description - description parameter which is contained in role description
     * @param createdBy - createdBy parameter which is contained in role createdBy
     * @param modifiedBy - modifiedBy which is contained in role modifiedBy
     * @param pageable - the pageable info to apply
     * @return the resulting page
     */
    public Page<Role> findByNameContainingOrDescriptionContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(String name, String description, String createdBy, String modifiedBy, Pageable pageable);

    /**
     * Finds roles that match with the following criteria
     * 
     * @param fromCreatedDate - fromCreatedDate as the starting date of role createdDate
     * @param toCreatedDate - toCreatedDate as the ending date of role createdDate
     * @param fromModifiedDate - fromModifiedDate as the starting date of role modifiedDate
     * @param toModifiedDate - toModifiedDate as the ending date of role modifiedDate
     * @param pageable - the pageable info to apply
     * @return the resulting page
     */
    public Page<Role> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
}
