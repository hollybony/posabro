/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.i18n;

import com.posabro.i18n.LocaleLocator;
import java.util.Locale;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Carlos Juarez
 */
public class WebLocaleLocator implements LocaleLocator{

    @Override
    public Locale lookLocale() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(sra.getRequest());
        Locale locale = localeResolver.resolveLocale(sra.getRequest());
        return locale;
    }
 
}
