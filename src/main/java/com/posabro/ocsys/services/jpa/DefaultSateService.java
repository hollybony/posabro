/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StatePK;
import com.posabro.ocsys.repository.StateRepository;
import com.posabro.ocsys.services.StateService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>GroupService</code>
 * 
 * @author Carlos Juarez
 */
@Service("stateService")
@Repository
@Transactional
public class DefaultSateService implements StateService{
   
    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultSateService.class);

    /**
     * The stateRepository
     */
    @Autowired
    private StateRepository stateRepository;
    
    /**
     * @see StateService#findStateById(com.posabro.ocsys.domain.StatePK) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public State findStateById(StatePK id) {
        return stateRepository.findOne(id);
    }

    /**
     * @see StateService#getAllStates() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<State> getAllStates() {
        return Lists.newArrayList(stateRepository.findAll()); 
    }

    /**
     * @see StateService#saveState(com.posabro.ocsys.domain.State) 
     * 
     * @param state 
     */
    @Override
    public void saveState(State state) {
        if(!stateRepository.exists(state.getStatePK())){
            stateRepository.save(state);
        }
        else{
            throw new JpaSystemException(new PersistenceException("state " + state.getStatePK() + " already exists"));
        }
    }

    /**
     * @see StateService#updateState(com.posabro.ocsys.domain.State) 
     * 
     * @param state 
     */
    @Override
    public void updateState(State state) {
        stateRepository.save(state);
    }

    /**
     * @see StateService#removeState(com.posabro.ocsys.domain.StatePK) 
     * 
     * @param id 
     */
    @Override
    public void removeState(StatePK id) {
        stateRepository.delete(id);
    }
    
}
