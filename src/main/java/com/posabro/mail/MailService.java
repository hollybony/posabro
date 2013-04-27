/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.mail;

/**
 * Sends email's
 * 
 * @author Carlos Juarez
 */
public interface MailService {
    
    /**
     * Send an email to the email address specified
     * 
     * @param emailAddress - the email address to which the email will be sent
     * @param subject - the subject of the email
     * @param content - the content of the email
     */
    public void sendEmail(String emailAddress, String subject, String content);
    
    /**
     * Send an email with an attached file to the email address specified
     * 
     * @param emailAddress - the email address to which the email will be sent
     * @param subject - the subject of the email
     * @param content - the content of the email
     * @param fileName = path on the system that targets the file that will be attached
     */
    public void sendEmail(String emailAddress, String subject, String content, String fileName);
    
}
