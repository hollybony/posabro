/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.i18n;

import java.util.Locale;

/**
 * Locator of <code>Locale</code>
 * 
 * @author Carlos Juarez
 */
public interface LocaleLocator {
    
    /**
     * @return the <code>Locale</code> found
     */
    public Locale lookLocale();
    
}
