/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.web.commons;

/**
 * Miscellaneous class which contains helper methods of different contexts
 * 
 * @author Carlos Juarez
 */
public class Misc {
    
    /**
     * Creates a <code>JQueryPage</code> from a <code>org.springframework.data.domain.Page</code>
     * 
     * @param page - the page from which the <code>JQueryPage</code> will be created
     * @return the jQueryPage
     */
    public static JQueryPage pageToJQueryPage(org.springframework.data.domain.Page<?> page, String echo){
        JQueryPage pageToReturn = new JQueryPage(page.getContent(), echo,(int) page.getTotalElements());
        return pageToReturn;
    }
    
}
