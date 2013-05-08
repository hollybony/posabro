/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services;

import com.posabro.ocsys.domain.Container;
import java.util.List;

/**
 *
 * @author Carlos Juarez
 */
public interface ContainerService {

    public Container findContainer(String id);

    public List<Container> getAllContainers();

    public void saveContainer(Container container);

    public void updateContainer(Container container);

    public void removeContainer(String id);
    
}
