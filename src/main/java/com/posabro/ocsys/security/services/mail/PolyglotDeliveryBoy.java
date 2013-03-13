/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.services.mail;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Carlos
 */
public class PolyglotDeliveryBoy extends DeliveryBoyImpl implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    protected String resolveString(final String key, Object...args) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(sra.getRequest());
        Locale locale = localeResolver.resolveLocale(sra.getRequest());
        logger.debug("looking for string " + key + " locale " + locale + " in " + messageSource.getClass());
        return messageSource.getMessage(key, args, locale);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
