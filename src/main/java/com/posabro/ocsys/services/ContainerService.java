/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.domain.ContainerType;
import java.util.List;

/**
 * Contains all the service methods of <code>Container</code>s
 * 
 * @author Carlos Juarez
 */
public interface ContainerService {

    /**
     * Finds a container by id
     * 
     * @param id - the id with which the container is looked for
     * @return the containers found
     */
    public Container findContainer(String id);
    
    /**
     * Gets the different container types
     * 
     * @return the container types
     */
    public List<ContainerType> getContainerTypes();

    /**
     * Gets all the containers
     * 
     * @return the containers found
     */
    public List<Container> getAllContainers();

    /**
     * Saves a container
     * 
     * @param container - the container to save
     */
    public void saveContainer(Container container);

    /**
     * Updates a container
     * 
     * @param container - the container to update
     */
    public void updateContainer(Container container);

    /**
     * Removes a container
     * 
     * @param id - the id of the container to be removed
     */
    public void removeContainer(String id);
    
}
