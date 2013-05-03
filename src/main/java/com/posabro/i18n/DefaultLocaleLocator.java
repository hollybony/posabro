/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.i18n;

import java.util.Locale;

/**
 *
 * @author Carlos Juarez
 */
public class DefaultLocaleLocator implements LocaleLocator{

    @Override
    public Locale lookLocale() {
        return Locale.US;
    }
    
}
