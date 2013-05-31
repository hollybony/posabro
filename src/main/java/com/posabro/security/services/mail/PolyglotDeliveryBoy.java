/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.security.services.mail;

import com.posabro.i18n.LocaleLocator;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

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
     * The localeLocator
     */
    private LocaleLocator localeLocator;

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
        Locale locale = localeLocator.lookLocale();
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
    
    /**
     * 
     * @param localeLocator - the localeLocator to set
     */
    public void setLocaleLocator(LocaleLocator localeLocator) {
        this.localeLocator = localeLocator;
    }
}
