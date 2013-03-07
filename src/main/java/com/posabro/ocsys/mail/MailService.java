/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.mail;

/**
 *
 * @author Carlos
 */
public interface MailService {
    
    public void sendEmail(String emailAddress, String subject, String content);
    
    public void sendEmail(String emailAddress, String subject, String content, String fileName);
    
}
