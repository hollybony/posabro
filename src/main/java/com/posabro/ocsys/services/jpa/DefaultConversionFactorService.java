/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.ConversionFactor;
import com.posabro.ocsys.domain.ConversionFactorId;
import com.posabro.ocsys.repository.ConversionFactorRepository;
import com.posabro.ocsys.services.ConversionFactorService;
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
@Service("conversionFactorService")
@Repository
@Transactional
public class DefaultConversionFactorService implements ConversionFactorService{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultConversionFactorService.class);

    @Autowired
    private ConversionFactorRepository conversionFactorRepository;
    
    @Override
    @Transactional(readOnly=true)
    public ConversionFactor findConversionFactor(ConversionFactorId id) {
        return conversionFactorRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<ConversionFactor> getAllConversionFactors() {
        return Lists.newArrayList(conversionFactorRepository.findAll());
    }

    @Override
    public void saveConversionFactor(ConversionFactor conversionFactor) {
        if(!conversionFactorRepository.exists(conversionFactor.getConversionFactorId())){
            conversionFactorRepository.save(conversionFactor);
        }else{
            throw new JpaSystemException(new PersistenceException("conversionFactor " + conversionFactor.getConversionFactorId() + " already exists"));
        }
    }

    @Override
    public void updateConversionFactor(ConversionFactor conversionFactor) {
        conversionFactorRepository.save(conversionFactor);
    }

    @Override
    public void removeConversionFactor(ConversionFactorId id) {
        conversionFactorRepository.delete(id);
    }
    
}
