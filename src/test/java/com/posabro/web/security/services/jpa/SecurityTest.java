/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.services.jpa;

import static org.junit.Assert.assertNotNull;
import com.posabro.services.AbstractServiceTest;
import com.posabro.security.domain.User;
import com.posabro.security.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class SecurityTest extends AbstractServiceTest{
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testInitialLoad(){
        User foundUser = userService.findUser("admin");
        assertNotNull("The admin user was not found", foundUser);
    }
    
}
