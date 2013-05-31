/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.i18n;

import java.util.Locale;

/**
 * Default implementation of <code>LocaleLocator</code> suitable for testing purpose
 * 
 * @author Carlos Juarez
 */
public class DefaultLocaleLocator implements LocaleLocator{

    /**
     * @return the default system <code>Locale</code>
     */
    @Override
    public Locale lookLocale() {
        return Locale.getDefault();
    }
    
}
