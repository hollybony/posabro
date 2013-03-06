/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.repository;

import com.posabro.ocsys.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Carlos Juarez
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    
    public Page<User> findByNameContainingOrEmailContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(String name, String email, String createdBy, String modifiedBy, Pageable pageable);

    public Page<User> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
    @Query("select u from User u join u.roles r where r.name = :roleName")
    public List<User> findByRoleName(@Param("roleName") String roleName);
    
}
