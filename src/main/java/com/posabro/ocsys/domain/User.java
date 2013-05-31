/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.domain;

/**
 * Represents a user
 * 
 * @author Carlos Juarez
 */
public class User {
    
    /**
     * The name
     */
    private String name;
    
    /**
     * The branch
     */
    private Branch branch;
    
    /**
     * Constructs an instance of <code>User</code> class
     */
    public User(){
        this(null, null);
    }
    
    /**
     * Constructs an instance of <code>User</code> class
     * 
     * @param name - the name to set
     * @param branch - the branch to set
     */
    public User(String name, Branch branch){
        this.name = name;
        this.branch = branch;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the branch
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * @param branch - the branch to set
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
