/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.repository;

import com.posabro.security.domain.Group;
import com.posabro.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Contains all the repository methods related to <code>Group</code>s
 * 
 * @author Carlos Juarez
 */
public interface GroupRepository extends CrudRepository<Group, Long> {
   
    /**
     * Finds groups that match with the following criteria
     * 
     * @param id - id parameter that matches with group id
     * @param name - name parameter which is contained in group name
     * @param createdBy - createdBy parameter which is contained in group createdBy
     * @param modifiedBy - modifiedBy which is contained in group modifiedBy
     * @param pageable - the pageable info to apply
     * @return the resulting page 
     */
    public Page<Group> findByIdOrNameContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(Long id, String name, String createdBy, String modifiedBy, Pageable pageable);

    /**
     * Finds groups that match with the following criteria
     * 
     * @param fromCreatedDate - fromCreatedDate as the starting date of group createdDate
     * @param toCreatedDate - toCreatedDate as the ending date of group createdDate
     * @param fromModifiedDate - fromModifiedDate as the starting date of group modifiedDate
     * @param toModifiedDate - toModifiedDate as the ending date of group modifiedDate
     * @param pageable - the pageable info to apply
     * @return the resulting page 
     */
    public Page<Group> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
    /**
     * Finds groups that contain roles which names match with roleName
     * 
     * @param roleName - role name to be matched
     * @return the groups found
     */
    @Query("select g from Group g join g.roles r where r.name = :roleName")
    public List<Group> findByRoleName(@Param("roleName") String roleName);

    /**
     * Finds members that matches with memberName
     * 
     * @param memberName - the memberName to match
     * @return the members found
     */
    @Query("select m from Group g join g.members m where m.name = :memberName")
    public List<User> findByMemberName(@Param("memberName") String memberName);
    
}
