/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.services;

import com.posabro.security.domain.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Contains all the service methods of <code>User</code>s
 * 
 * @author Carlos Juarez
 */
public interface UserService {
    
    /**
     * Queries the users that match with the string pattern in the properties that apply as well as the page
     * constraints
     * 
     * @param pattern - the string pattern
     * @param pageable - the page constraint to apply
     * @return the page generated
     */
    public Page<User> queryPageByStringPattern(String pattern, Pageable pageable);
    
    /**
     * Queries the users that match with the date pattern in the properties that apply as well as the page
     * constraints
     * 
     * @param datePattern - the date pattern
     * @param pageable - the page constraints to apply
     * @return the page generated
     */
    public Page<User> queryPageByDatePattern(Date datePattern, Pageable pageable);
    
    /**
     * Registers a user
     * 
     * @param user - the user to be registered
     */
    public void registerUser(User user);
    
    /**
     * Register a user as guest
     * 
     * @param user - the user to be registered as guest 
     */
    public void registerGuest(User user);
    
    /**
     * Update a user
     * 
     * @param user - the user to update
     */
    public void updateUser(User user);

    /**
     * Gets all the users
     * 
     * @return users found
     */
    public List<User> getAllUsers();

    /**
     * Removes a user
     * 
     * @param name - name of the user to be removed
     */
    public void removeUser(String name);

    /**
     * Finds a user
     * 
     * @param name - name of the user to found
     * @return user found
     */
    public User findUser(String name);
    
    /**
     * Verifies an email address
     * 
     * @param token - the token generated when the email address was updated
     * @param userName - the user name of the address being verified
     * @return the user verified
     */
    public User verifyEmailAddress(String token, String userName);
    
    /**
     * Generates a temporary password for the user given
     * 
     * @param userName - user for whom the temp password is going to be generated
     * @param emailAddress - email address where the temp password is sent
     */
    public void generateTempPassword(String userName, String emailAddress);
    
    /**
     * Verifies if the user name given has a temporary password that besides match with the token given
     * 
     * @param userName - user name
     * @param token - token
     * @return <code>true</code> if the user has a temp password otherwise <code>false</code>
     */
    public boolean isVerifiedTempPassword(String userName, String token);

    /**
     * Sets a new normal password to a user that had a temporary password
     * 
     * @param userName - user name
     * @param tempPassword - the temporary password
     * @param newPassword - the new password
     * @param token - token to verify the temporary password
     */
    public void retrievePassword(String userName, char[] tempPassword, char[] newPassword, String token);
}
