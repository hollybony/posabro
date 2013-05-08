/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.google.common.collect.Lists;
import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.repository.ContainerRepository;
import com.posabro.ocsys.services.ContainerService;
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
@Service("containerService")
@Repository
@Transactional
public class DefaultContainerService implements ContainerService{
    
    final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultContainerService.class);
    
    @Autowired
    private ContainerRepository containerRepository;

    @Override
    @Transactional(readOnly=true)
    public Container findContainer(String id) {
        return containerRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Container> getAllContainers() {
        return Lists.newArrayList(containerRepository.findAll());
    }

    @Override
    public void saveContainer(Container container) {
        if(!containerRepository.exists(container.getId())){
            containerRepository.save(container);
        }else{
            throw new JpaSystemException(new PersistenceException("container " + container.getId() + " already exists"));
        }
    }

    @Override
    public void updateContainer(Container container) {
        containerRepository.save(container);
    }

    @Override
    public void removeContainer(String id) {
        containerRepository.delete(id);
    }
    
}
