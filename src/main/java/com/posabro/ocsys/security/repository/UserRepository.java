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
    
    public Page<User> findByNameContaining(String name, Pageable pageable);

    public Page<User> findByCreationDate(Date creationDate, Pageable pageable);
    
}
