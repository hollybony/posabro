/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.commons;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.DigestUtils;

/**
 * This class is taken from the Spring Security 3 book. Basically used to add a
 * little more security in the process of remember me function. The idea is to
 * include user IP address to the token which is hold in a cookie. To configure
 * this service you need to declare it as bean
 *
 * <bean class="com.posabro.web.commons.IPTokenBasedRememberMeServices"
 *  id="ipTokenBasedRememberMeServicesBean">
 *  <property name="key"><value>jbcpPetStore</value></property>
 *  <property name="userDetailsService" ref="userService"/>
 * </bean>
 * 
 * Then in the Spring Security XML configuration file.
 * Modify the <remember-me> element to reference our custom Spring Bean as follows:
 * <remember-me key="cosysRememberorb:friTh?56paEan10ORs:" services-ref="ipTokenBasedRememberMeServicesBean"/>
 *
 * Finally, add an id attribute to the <user-service> declaration, if it's not
 * already there:
 * <user-service id="userService">
 *
 * @author Carlos Juarez
 */
public class IPTokenBasedRememberMeServices extends TokenBasedRememberMeServices {

    /**
     * The requestHolder to hold a reference of current
     * <code>HttpServletRequest</code>
     */
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    /**
     * @return the current <code>HttpServletRequest</code>
     */
    public HttpServletRequest getContext() {
        return requestHolder.get();
    }

    /**
     * @param context - the <code>HttpServletRequest</code> to set
     */
    public void setContext(HttpServletRequest context) {
        requestHolder.set(context);
    }

    /**
     * @param request - where the IP address is taken from
     *
     * @return the IP address
     */
    protected String getUserIPAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    /**
     * In this method, we simply need to set the ThreadLocal and clear it after
     * we're done. Keep in mind the parent method's flow—to aggregate all the
     * information about the user's authenticated request and synthesize that
     * into a cookie
     *
     * @param request - the current request
     * @param response - the current response
     * @param successfulAuthentication - authentication
     */
    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        try {
            setContext(request);
            super.onLoginSuccess(request, response, successfulAuthentication);
        } finally {
            setContext(null);
        }
    }

    /**
     * Build the token signature which includes the user name, password, the key
     * and the IP address. This method is called by parent's onLoginSuccess
     * method
     *
     * @param tokenExpiryTime - then tokenExpiryTime
     * @param username - the username
     * @param password - the password
     * @return the token generated
     */
    @Override
    protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        return DigestUtils.md5DigestAsHex((username + ":"
                + tokenExpiryTime + ":" + password + ":" + getKey() + ":" + getUserIPAddress(getContext())).getBytes());
    }

    /**
     * It adds an additional encoded bit that includes the requesting IP address
     *
     * @param tokens - the tokens
     * @param maxAge - age of the cookie
     * @param request - current request
     * @param response - - the current response where the cookie will be set
     */
    @Override
    protected void setCookie(String[] tokens, int maxAge,
            HttpServletRequest request, HttpServletResponse response) {
        // append the IP adddress to the cookie
        String[] tokensWithIPAddress =
                Arrays.copyOf(tokens, tokens.length + 1);
        tokensWithIPAddress[tokensWithIPAddress.length - 1] =
                getUserIPAddress(request);
        super.setCookie(tokensWithIPAddress, maxAge,
                request, response);
    }

    /**
     * validates the contents of the remember me cookie that the user provided.
     * Looks for the matching of the IP address that comes with the cookie and
     * the user IP address then invoke the parent's processAutoLoginCookie
     * method
     *
     * @param cookieTokens - the cookie tokens
     * @param request - the current request
     * @param response - the current response
     * @return the user details
     */
    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        try {
            setContext(request);
// take off the last token
            String ipAddressToken =
                    cookieTokens[cookieTokens.length - 1];
            if (!getUserIPAddress(request).equals(ipAddressToken)) {
                throw new InvalidCookieException("Cookie IP Address did not contain a matching IP (contained '" + ipAddressToken + "')");
            }
            return super.processAutoLoginCookie(Arrays.copyOf(cookieTokens,
                    cookieTokens.length - 1), request, response);
        } finally {
            setContext(null);
        }
    }
}
