/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

/**
 *
 * @author Carlos
 */
public class Misc {
    
    /**
     *
     * @param page
     * @return
     */
    public static JQueryPage pageToJQueryPage(org.springframework.data.domain.Page<?> page, String echo){
        JQueryPage pageToReturn = new JQueryPage(page.getContent(), echo,(int) page.getTotalElements());
        return pageToReturn;
    }
    
}
