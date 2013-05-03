/**
 * Created on Nov 1, 2011
 */
package com.posabro.web.security.services.jpa;

import com.posabro.web.security.domain.User;
import com.posabro.web.security.services.UserService;
import java.util.Arrays;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Carlos Juarez
 */
public class DefaultUserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void testRegisterGuest() {
        User user = new User("Nacho", "nacho".toCharArray());
        user.getAuditData().setCreatedBy("anonymous");
        user.getAuditData().setCreatedDate(new Date());
        user.setEmail("carloantonioj@gmail.com");
        userService.registerGuest(user);
        User foundUser = userService.findUser("Nacho");
        assertNotNull(foundUser);
        assertTrue(foundUser.getName().equals("Nacho"));
        assertTrue(foundUser.getEmail().equals("carloantonioj@gmail.com"));
        assertTrue("user password is not matching", Arrays.equals(foundUser.getPassword(), "b623e24add2f342de2acdf8b4edad496".toCharArray()));
    }
    
  
    
}
