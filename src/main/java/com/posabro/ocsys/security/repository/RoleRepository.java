/**
 * Created on Dec 13, 2011
 */
package com.posabro.ocsys.security.repository;

import com.posabro.ocsys.security.domain.Role;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Carlos Juarez
 *
 */
public interface RoleRepository extends CrudRepository<Role, String> {
    
    public Page<Role> findByNameContainingOrDescriptionContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(String name, String description, String createdBy, String modifiedBy, Pageable pageable);

    public Page<Role> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
}
