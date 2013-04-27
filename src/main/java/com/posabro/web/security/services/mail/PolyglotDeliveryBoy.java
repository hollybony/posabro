/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.security.services.mail;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * I18n version of <code>DeliveryBoyImpl</code>
 * 
 * @author Carlos Juarez
 */
public class PolyglotDeliveryBoy extends DeliveryBoyImpl implements MessageSourceAware {

    /**
     * The messageSource
     */
    private MessageSource messageSource;

    /**
     * The strings are taken from the messageSource. The locale is the current locale provided by
     * the localeResolver
     * 
     * @param key - the key of the string to found
     * @param args - arguments to replace in the string found
     * @return the string found
     */
    @Override
    protected String resolveString(final String key, Object...args) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(sra.getRequest());
        Locale locale = localeResolver.resolveLocale(sra.getRequest());
        logger.debug("looking for string " + key + " locale " + locale + " in " + messageSource.getClass());
        return messageSource.getMessage(key, args, locale);
    }

    /**
     * @param messageSource - the messageSource to set
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
