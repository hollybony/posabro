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
import org.springframework.context.support.StaticMessageSource;

/**
 *
 * @author Carlos
 */
public class CodeMessageSource implements MessageSource{

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return getMessage(code,args,locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return code.replace(".", " ").concat(Arrays.toString(args));
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        throw new UnsupportedOperationException("I am afraid we don't deal with this kind of things");
    }
    
}
