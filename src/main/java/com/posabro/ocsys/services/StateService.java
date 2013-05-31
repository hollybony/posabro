/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StatePK;
import java.util.List;

/**
 * Contains all the service methods of <code>State</code>s
 * 
 * @author Carlos Juarez
 */
public interface StateService {
    
    /**
     * Finds a state by id
     * 
     * @param id - the id with which the state is looked for
     * @return 
     */
    public State findStateById(StatePK id);
    
    /**
     * Gets all the states
     * 
     * @return the states found
     */
    public List<State> getAllStates();
    
    /**
     * Saves an state
     * 
     * @param state - the state to save
     */
    public void saveState(State state);
    
    /**
     * Updates a state
     * 
     * @param state - state to update
     */
    public void updateState(State state);
    
    /**
     * Removes a state
     * 
     * @param id - the id of the state to be removed
     */
    public void removeState(StatePK id);
    
}
