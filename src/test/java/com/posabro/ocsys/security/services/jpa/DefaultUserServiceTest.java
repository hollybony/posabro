/**
 * Created on Nov 1, 2011
 */
package com.posabro.ocsys.security.services.jpa;

import com.posabro.ocsys.security.domain.Role;
import com.posabro.ocsys.security.domain.User;
import com.posabro.ocsys.security.services.UserService;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Clarence
 *
 */
public class DefaultUserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
//        User user = new User("Nacho", "nacho".toCharArray(), Arrays.asList(new Role()), new Date());
//        userService.saveUser(user);
//        System.out.println("User: " + user);
    }
}
