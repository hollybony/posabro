/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.mail;

import com.posabro.ocsys.mail.MailService;
import com.posabro.ocsys.security.services.DeliveryBoy;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Carlos
 */
public class DeliveryBoyImpl implements DeliveryBoy {

    private MailService mailService;
    
    private String url;
    
    private String title;

    @Override
    public void sendEmailVerification(String userName, String emailAdrress, String key) {
        String title = this.title.replace("{emailAddress}", userName);
        String url = this.url.replace("{userName}", userName).replace("{key}", key);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hey, we want to verify that you are indeed \"%s\".  If that's the case, please follow the link below:", userName));
        sb.append(System.getProperty("line.separator"));
        sb.append(url);
        sb.append(System.getProperty("line.separator"));
        sb.append(String.format("If you're not %s or didn't request verification you can ignore this email.", userName));
        mailService.sendEmail(emailAdrress, title, sb.toString());
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * The URL. Consider to use placeholders to modify the URL, example:
     * 
     * http://myduckisdead/{userName}/{key}
     * 
     * @param url 
     */
    public void setUrl(String url) {
        try {
            URL urL = new URL(url);
            this.url = url;
            urL = null;
        } catch (MalformedURLException ex) {
            throw new RuntimeException("invalid URL",ex);
        }
    }

    /**
     * The title of the email. Consider to place a placeholder include the email address, example:
     * 
     * Dear Mama my email address is {emailAddress}
     * 
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void sendTempPassword(String userName, String emailAddress, String key, char[] password) {
        String title = "[Posabro] Retrieve password {userName}".replace("{userName}", userName);
        String url = "http://localhost:8080/cosys/userController/verifyTempPasswordKey/{userName}/{key}".
                replace("{userName}", userName).replace("{key}", key);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hey %s, we know your forgot your password. Here you have a temporary one:", userName));
        sb.append(System.getProperty("line.separator"));
        sb.append(String.copyValueOf(password));
        sb.append(System.getProperty("line.separator"));
        sb.append("Just click the following link and follow the instructions");
        sb.append(System.getProperty("line.separator"));
        sb.append(url);
        sb.append(System.getProperty("line.separator"));
        mailService.sendEmail(emailAddress, title, sb.toString());
    }
}
