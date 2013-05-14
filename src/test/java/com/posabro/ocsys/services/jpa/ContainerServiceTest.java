/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.posabro.ocsys.services.jpa;

import com.posabro.ocsys.domain.Container;
import com.posabro.ocsys.services.ContainerService;
import com.posabro.services.AbstractServiceTest;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    
    private final Container aContainer = new Container("BLA", new BigDecimal("12.2"), new BigDecimal("32.2"), new BigDecimal("2.1"));
    
    private final List<Container> someContainers = Arrays.asList(
            new Container("C1", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Container("C2", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Container("C3", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Container("C4", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
    
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
        assertEquals(new BigDecimal("12.20"), foundContainer.getLtsFillCapacity());
        assertEquals(new BigDecimal("32.20"), foundContainer.getLtsFullCapacity());
        assertEquals(new BigDecimal("2.10"), foundContainer.getTareWeight());
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
