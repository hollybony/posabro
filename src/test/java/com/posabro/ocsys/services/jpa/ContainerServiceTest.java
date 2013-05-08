/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.services.ContainerService;
import com.posabro.services.AbstractServiceTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos Juarez
 */
public class ContainerServiceTest extends AbstractServiceTest{
    
    private final Container aContainer = new Container("BLA", 12.2, 32.2, 2.1);
    
    private final List<Container> someContainers = Arrays.asList(new Container("C1", 0, 0, 0), new Container("C2", 0, 0, 0),
            new Container("C3", 0, 0, 0),new Container("C4", 0, 0, 0));
    
    @Autowired
    private ContainerService containerService;
    
    @Test
    public void testContainerServiceUpdates(){
        //save
        containerService.saveContainer(aContainer);
        //find
        Container foundContainer = containerService.findContainer("BLA");
        assertNotNull(foundContainer);
        assertEquals("BLA", foundContainer.getId());
        assertTrue(12.2==foundContainer.getLtsFillCapacity());
        assertTrue(32.2==foundContainer.getLtsFullCapacity());
        assertTrue(2.1==foundContainer.getTareWeight());
        //remove
        containerService.removeContainer("BLA");
        assertNull(containerService.findContainer("BLA"));
    }
    
    @Test
    public void testContainerServiceQueries(){
        for(Container container : someContainers){
            containerService.saveContainer(container);
        }
        List<Container> allContainers = containerService.getAllContainers();
        assertTrue(allContainers.size()==someContainers.size());
        //cleanup
        for(Container container : allContainers){
            containerService.removeContainer(container.getId());
        }
    }
    
}
