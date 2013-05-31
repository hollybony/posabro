/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.i18n;

import java.util.Arrays;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 * For testing purpose. This message source returns the codes as message
 * @author Carlos Juarez
 */
public class CodeMessageSource implements MessageSource{

    /**
     * Delegates the call to 
     * @see CodeMessageSource#getMessage(java.lang.String, java.lang.Object[], java.util.Locale) 
     * 
     * @param code - the code
     * @param args - the args
     * @param defaultMessage - is ignored
     * @param locale - the locale
     * @return the messages
     */
    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return getMessage(code, args, locale);
    }

    /**
     * Returns the code parameter as the message replacing the dots to spaces
     * @param code - the code
     * @param args - the args
     * @param locale - the locale
     * @return the message generated
     * @throws NoSuchMessageException never happens
     */
    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return code.replace(".", " ").concat(Arrays.toString(args));
    }

    /**
     * Always throws <code>UnsupportedOperationException</code>
     * 
     * @param resolvable 
     * @param locale
     * @return
     * @throws NoSuchMessageException 
     */
    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        throw new UnsupportedOperationException("I am afraid we don't deal with this kind of things");
    }
    
}
