/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.services.mail;

import com.posabro.mail.MailService;
import com.posabro.security.services.DeliveryBoy;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Juarez
 */
public class DeliveryBoyImpl implements DeliveryBoy {
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DeliveryBoyImpl.class);

    /**
     * The mailService
     */
    private MailService mailService;
    
    /**
     * @see DeliveryBoy#sendEmailVerification(java.lang.String, java.lang.String, java.lang.String) 
     * The data for the email is hard coded
     * 
     * @param userName
     * @param emailAdrress
     * @param token 
     */
    @Override
    public void sendEmailVerification(String userName, String emailAdrress, String token) {
        String subject = resolveString("emailVerification.subject",userName);
        String url = resolveString("emailVerification.url",userName,token);
        String content = resolveString("emailVerification.content",userName,url);
        mailService.sendEmail(emailAdrress, subject, content);
    }

    /**
     * @see DeliveryBoy#sendTempPassword(java.lang.String, java.lang.String, java.lang.String, char[]) 
     * The data for the email is hard coded
     * 
     * @param userName
     * @param emailAddress
     * @param token
     * @param password 
     */
    @Override
    public void sendTempPassword(String userName, String emailAddress, String token, char[] password) {
        String subject = resolveString("tempPassword.subject",userName);
        String url = resolveString("tempPassword.url",userName, token);
        String content = resolveString("tempPassword.content",userName,new String(password),url);
        mailService.sendEmail(emailAddress, subject, content);
    }

    /**
     * @param mailService - the mailService to set
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    
    /**
     * Provides all the different String. This implementation comes with the strings hard coded
     * 
     * @param key - the key of the string to find
     * @param args - arguments to replace in the string found
     * @return the string found
     */
    protected String resolveString(final String key, Object...args){
        if(key.equals("emailVerification.subject")){
            return String.format("[Posabro] Please verify your email '%s'",args);
        }else if(key.equals("emailVerification.url")){
            return String.format("http://localhost:8080/cosys/userController/confirmEmailAddress/%s/%s",args);
        }else if(key.equals("emailVerification.content")){
            return String.format("Hey, we want to verify that you are indeed %s.  If that's the case, please follow the link below:\n%s",args);
        }else if(key.equals("tempPassword.subject")){
            return String.format("[Posabro] Retrieve password %s",args);
        }else if(key.equals("tempPassword.url")){
            return String.format("http://localhost:8080/cosys/userController/verifyTempPasswordToken/%s/%s",args);
        }else if(key.equals("tempPassword.content")){
            return String.format("Hey %s, we know your forgot your password. Here you have a temporary one:" +
                    "\n%s\nJust click the following link and follow the instructions\n%s",args);
        }
        throw new RuntimeException(String.format("The key %s was not found", key));
    }

}
