/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.repository;

import com.posabro.ocsys.security.domain.User;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Carlos Juarez
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    
    public Page<User> findByNameContainingOrEmailContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(String name, String email, String createdBy, String modifiedBy, Pageable pageable);

    public Page<User> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
}
