/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.mail;

import com.posabro.ocsys.mail.MailService;
import com.posabro.ocsys.security.services.ConfirmationEmailSender;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Carlos
 */
public class ConfirmationEmailSenderImpl implements ConfirmationEmailSender {

    private MailService mailService;
    
    private String url;
    
    private String title;

    @Override
    public void sendEmail(String userName, String emailAdrress, String key) {
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
}
