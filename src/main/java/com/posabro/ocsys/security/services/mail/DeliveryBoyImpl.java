/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.mail;

import com.posabro.ocsys.mail.MailService;
import com.posabro.ocsys.security.services.DeliveryBoy;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos
 */
public class DeliveryBoyImpl implements DeliveryBoy {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DeliveryBoyImpl.class);

    private MailService mailService;
    
    @Override
    public void sendEmailVerification(String userName, String emailAdrress, String key) {
        String subject = resolveString("emailVerification.subject",userName);
        String url = resolveString("emailVerification.url",userName,key);
        String content = resolveString("emailVerification.content",userName,url);
        mailService.sendEmail(emailAdrress, subject, content);
    }

    @Override
    public void sendTempPassword(String userName, String emailAddress, String key, char[] password) {
        String subject = resolveString("tempPassword.subject",userName);
        String url = resolveString("tempPassword.url",userName, key);
        String content = resolveString("tempPassword.content",userName,new String(password),url);
        mailService.sendEmail(emailAddress, subject, content);
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    
    protected String resolveString(final String key, Object...args){
        if(key.equals("emailVerification.subject")){
            return String.format("[Posabro] Please verify your email '%s'",args);
        }else if(key.equals("emailVerification.url")){
            return String.format("http://localhost:8080/cosys/userController/confirmEmailAddress/%s/%s",args);
        }else if(key.equals("emailVerification.content")){
            return String.format("Hey, we want to verify that you are indeed %s.  If that's the case, please follow the link below:" +
                    "\n%s\n If you're not %s or didn't request verification you can ignore this email",args);
        }else if(key.equals("tempPassword.subject")){
            return String.format("[Posabro] Retrieve password %s",args);
        }else if(key.equals("tempPassword.url")){
            return String.format("http://localhost:8080/cosys/userController/verifyTempPasswordKey/%s/%s",args);
        }else if(key.equals("tempPassword.content")){
            return String.format("Hey %s, we know your forgot your password. Here you have a temporary one:" +
                    "\n%s\nJust click the following link and follow the instructions\n%s",args);
        }
        throw new RuntimeException(String.format("The key %s was not found", key));
    }

}
