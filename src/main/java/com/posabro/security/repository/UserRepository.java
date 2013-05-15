/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.repository;

import com.posabro.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Contains all the repository methods related to <code>User</code>s
 *
 * @author Carlos Juarez
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    
    /**
     * Finds users that match with the following criteria
     * 
     * @param name - name parameter which is contained in user name
     * @param email - email parameter which is contained in user email address
     * @param createdBy - createdBy parameter which is contained in user createdBy
     * @param modifiedBy - modifiedBy which is contained in user modifiedBy
     * @param pageable - the pageable info to apply
     * @return the resulting page
     */
    public Page<User> findByNameContainingOrEmailContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(String name, String email, String createdBy, String modifiedBy, Pageable pageable);

    /**
     * Finds users that match with the following criteria
     * 
     * @param fromCreatedDate - fromCreatedDate as the starting date of user createdDate
     * @param toCreatedDate - toCreatedDate as the ending date of user createdDate
     * @param fromModifiedDate - fromModifiedDate as the starting date of user modifiedDate
     * @param toModifiedDate - toModifiedDate as the ending date of user modifiedDate
     * @param pageable - the pageable info to apply
     * @return the resulting page
     */
    public Page<User> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
    /**
     * Finds all the users that have assigned the role named roleName
     * 
     * @param roleName - the roleName to look for
     * @return the users found
     */
    @Query("select u from User u join u.roles r where r.name = :roleName")
    public List<User> findByRoleName(@Param("roleName") String roleName);
    
}
