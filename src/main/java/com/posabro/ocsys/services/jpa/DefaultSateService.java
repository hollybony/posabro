/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Country;
import com.posabro.ocsys.domain.State;
import com.posabro.ocsys.domain.StateId;
import com.posabro.ocsys.repository.StateRepository;
import com.posabro.ocsys.services.StateService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Juarez
 */
@Service("stateService")
@Repository
@Transactional
public class DefaultSateService implements StateService{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultSateService.class);

    @Autowired
    private StateRepository stateRepository;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly=true)
    public State findState(StateId id) {
        return stateRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<State> getAllStates() {
        return Lists.newArrayList(stateRepository.findAll()); 
    }

    @Override
    public void saveState(State state) {
        if(!stateRepository.exists(state.getStateId())){
            stateRepository.save(state);
        }
        else{
            throw new JpaSystemException(new PersistenceException("state " + state.getStateId() + " already exists"));
        }
    }

    @Override
    public void updateState(State state) {
        stateRepository.save(state);
    }

    @Override
    public void removeState(StateId id) {
        stateRepository.delete(id);
    }
    
}
