/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services;

/**
 *
 * @author Carlos
 */
public interface ConfirmationEmailSender {
    
    public void sendEmail(String userName, String emailAdrress, String key);
    
}
