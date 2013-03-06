/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.repository;

import com.posabro.ocsys.security.domain.Group;
import com.posabro.ocsys.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Carlos
 */
public interface GroupRepository extends CrudRepository<Group, Long> {
    
    public Page<Group> findByIdOrNameContainingOrAuditDataCreatedByContainingOrAuditDataModifiedByContaining(Long id, String name, String createdBy, String modifiedBy, Pageable pageable);

    public Page<Group> findByAuditDataCreatedDateBetweenOrAuditDataModifiedDateBetween(Date fromCreatedDate, Date toCreatedDate, Date fromModifiedDate, Date toModifiedDate, Pageable pageable);
    
    @Query("select g from Group g join g.roles r where r.name = :roleName")
    public List<Group> findByRoleName(@Param("roleName") String roleName);

    @Query("select m from Group g join g.members m where m.name = :memberName")
    public List<User> findByMemberName(@Param("memberName") String memberName);
    
}
