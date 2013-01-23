/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.repository;

import com.posabro.ocsys.security.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Carlos
 */
public interface UserRepository extends CrudRepository<User, String> {
    
}
