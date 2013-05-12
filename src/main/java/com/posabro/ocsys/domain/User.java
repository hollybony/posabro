/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

/**
 *
 * @author Carlos Juarez
 */
public class User {
    
    private String name;
    
    private Branch branch;
    
    public User(){
        this(null, null);
    }
    
    public User(String name, Branch branch){
        this.name = name;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
