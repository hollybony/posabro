/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.SystemParameter;
import com.posabro.ocsys.domain.SystemParameterId;
import com.posabro.ocsys.repository.SystemParameterRepository;
import com.posabro.ocsys.services.SystemParameterService;
import java.util.List;
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
@Service("systemParameterService")
@Repository
@Transactional
public class DefaultSystemParameterService implements SystemParameterService{

    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultSystemParameterService.class);
    
    @Autowired
    private SystemParameterRepository systemParameterRepository;
    
    @Override
    @Transactional(readOnly=true)
    public SystemParameter findSystemParameter(SystemParameterId id) {
        return systemParameterRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<SystemParameter> getAllSystemParameters() {
        return Lists.newArrayList(systemParameterRepository.findAll());
    }

    @Override
    public void saveSystemParameter(SystemParameter systemParameter) {
        if(!systemParameterRepository.exists(systemParameter.getSystemParameterId())){
            systemParameterRepository.save(systemParameter);
        }else{
            throw new JpaSystemException(new PersistenceException("systemParameter " + systemParameter.getSystemParameterId() + " already exists"));
        }
    }

    @Override
    public void updateSystemParameter(SystemParameter systemParameter) {
        systemParameterRepository.save(systemParameter);
    }

    @Override
    public void removeSystemParameter(SystemParameterId id) {
        systemParameterRepository.delete(id);
    }
    
}
