/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

/**
 *
 * @author Carlos
 */
public interface DeliveryBoy {
    
    public void sendEmailVerification(String userName, String emailAdrress, String key);
    
    public void sendTempPassword(String userName, String emailAddress, String key, char[] password);
    
}
