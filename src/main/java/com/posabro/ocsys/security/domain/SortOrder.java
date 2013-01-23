/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.security.domain;

/**
 *
 * @author Carlos
 */
public class SortOrder {
    
    public enum Type{
        ASC,DESC
    }

    private Type type;

    private String column;
    
    public SortOrder() {
        this(Type.ASC,"");
    }
    
    public SortOrder(Type type, String column) {
        this.type = type;
        this.column = column;
    }
    
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
