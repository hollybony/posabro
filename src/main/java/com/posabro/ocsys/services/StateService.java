/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StatePK;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface StateService {
    
    public State findState(StatePK id);
    
    public List<State> getAllStates();
    
    public void saveState(State state);
    
    public void updateState(State state);
    
    public void removeState(StatePK id);
    
}
