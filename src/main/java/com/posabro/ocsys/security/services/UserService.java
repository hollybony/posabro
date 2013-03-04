/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

import com.posabro.ocsys.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Carlos Juarez
 */
public interface UserService {
    
//    public Page queryUsersPage(PageQuery pageQuery);
    
    public Page<User> queryPageByStringPattern(String pattern, Pageable pageable);
    
    public Page<User> queryPageByDatePattern(Date datePattern, Pageable pageable);
    
    public void saveUser(User user);
    
    public void updateUser(User user);

    public List<User> getAllUsers();

    public void removeUser(String name);

    public User findUser(String name);
}
