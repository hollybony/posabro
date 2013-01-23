/**
 * Created on Dec 13, 2011
 */
package com.posabro.ocsys.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.posabro.ocsys.security.domain.Role;

/**
 * @author Clarence
 *
 */
public interface RoleRepository extends CrudRepository<Role, String> {
}
