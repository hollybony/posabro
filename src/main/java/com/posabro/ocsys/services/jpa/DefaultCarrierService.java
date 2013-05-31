/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Carrier;
import com.posabro.ocsys.repository.CarrierRepository;
import com.posabro.ocsys.services.CarrierService;
import java.util.List;
import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Defaults implementation of <code>CarrierService</code>
 * 
 * @author Carlos Juarez
 */
@Service("carrierService")
@Repository
@Transactional
public class DefaultCarrierService implements CarrierService{

    /**
     * The logger
     */
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultCarrierService.class);
    
    /**
     * The carrierRepository
     */
    @Autowired
    private CarrierRepository carrierRepository;
    
    /**
     * @see CarrierService#findCarrier(java.lang.String) 
     * 
     * @param id
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public Carrier findCarrier(String id) {
        return carrierRepository.findOne(id);
    }

    /**
     * @see CarrierService#getAllCarriers() 
     * 
     * @return 
     */
    @Override
    @Transactional(readOnly=true)
    public List<Carrier> getAllCarriers() {
        return Lists.newArrayList(carrierRepository.findAll());
    }

    /**
     * @see CarrierService#saveCarrier(com.posabro.ocsys.domain.Carrier) 
     * 
     * @param carrier 
     */
    @Override
    public void saveCarrier(Carrier carrier) {
//        if(em.find(Carrier.class, carrier.getId())==null){
//            em.persist(carrier);
//        }
        if(!carrierRepository.exists(carrier.getId())){
            carrierRepository.save(carrier);
        }
        else{
            throw new JpaSystemException(new PersistenceException("carrier " + carrier.getId() + " already exists"));
        }
    }

    /**
     * @see CarrierService#updateCarrier(com.posabro.ocsys.domain.Carrier) 
     * 
     * @param carrier 
     */
    @Override
    public void updateCarrier(Carrier carrier) {
        carrierRepository.save(carrier);
    }

    /**
     * @see CarrierService#removeCarrier(java.lang.String) 
     * 
     * @param id 
     */
    @Override
    public void removeCarrier(String id) {
        carrierRepository.delete(id);
    }
    
}
