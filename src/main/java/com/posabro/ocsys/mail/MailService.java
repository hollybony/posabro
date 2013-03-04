/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author Carlos
 */
public class MailService {
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(MailService.class);
    
    private JavaMailSender mailSender;
    
    private String senderEmailAddress;
    
    public void sendEmail(String emailAddress, String subject, String content){
        sendEmail(emailAddress, subject, content, null);
    }
    
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

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }
    
}
