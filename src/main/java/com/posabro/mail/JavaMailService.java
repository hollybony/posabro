/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * <code>MailService</code> implementation that uses JavaMail to perform the mailing process
 * 
 * @author Carlos Juarez
 */
public class JavaMailService implements MailService{
    
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(JavaMailService.class);
    
    /**
     * The mailSender
     */
    private JavaMailSender mailSender;
    
    /**
     * Then sender email address
     */
    private String senderEmailAddress;
    
    /**
     * @see MailService#sendEmail(java.lang.String, java.lang.String, java.lang.String) 
     */
    @Override
    public void sendEmail(String emailAddress, String subject, String content){
        sendEmail(emailAddress, subject, content, null);
    }
    
    /**
     * @see MailService#sendEmail(java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    @Override
    public void sendEmail(String emailAddress, String subject, String content, String fileName){
        synchronized(mailSender){
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(senderEmailAddress);
                helper.setTo(emailAddress);
                helper.setSubject(subject);
                helper.setText(content);
                if(fileName!=null){
                    logger.debug("file to send : " + fileName);
                    FileSystemResource file = new FileSystemResource(fileName);
                    helper.addAttachment(file.getFilename(), file);
                }
                mailSender.send(mimeMessage);
            } catch (MessagingException ex) {
                logger.error("Exception while sending email", ex);
            }
        }
    }

    /**
     * @param mailSender - the mailSender to set
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param senderEmailAddress - the senderEmailAddress to set
     */
    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }
    
}
