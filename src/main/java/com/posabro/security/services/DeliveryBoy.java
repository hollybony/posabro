/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.services;

/**
 * Helper class that sends notifications related to users registration
 * 
 * @author Carlos Juarez
 */
public interface DeliveryBoy {
    
    /**
     * Sends email to verify if the user really owns the email address that he gave
     * 
     * @param userName - the user name
     * @param emailAdrress - email address where the email will be sent
     * @param token - a token that is required by the verification service
     */
    public void sendEmailVerification(String userName, String emailAdrress, String token);
    
    /**
     * Sends a temporary password to the email address given
     * 
     * @param userName - user name that will be receive the temp password
     * @param emailAddress - email address where the temp password is sent
     * @param token - token required at the moment the user wants to create a regular password
     * @param password the temporary password
     */
    public void sendTempPassword(String userName, String emailAddress, String token, char[] password);
    
}
