/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

import com.posabro.ocsys.security.domain.Page;
import com.posabro.ocsys.security.domain.PageQuery;
import com.posabro.ocsys.security.domain.User;

/**
 *
 * @author Carlos
 */
public interface UserService {
    
    public Page queryUsersPage(PageQuery pageQuery);

    public void saveUser(User user);
    
    public void updateUser(User user);

    public Iterable<User> getAllUsers();

    public void removeUser(String name);

    public User findUser(String name);
}
