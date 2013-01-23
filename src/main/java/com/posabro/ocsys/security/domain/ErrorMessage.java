/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

import java.io.Serializable;

/**
 *
 * @author Carlos
 */
public class ErrorMessage implements Serializable{
    
    private String message;
    
    public ErrorMessage(){
        this("");
    }
    
    public ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
