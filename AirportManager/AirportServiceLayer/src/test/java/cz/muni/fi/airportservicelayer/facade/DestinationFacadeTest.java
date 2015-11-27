/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.facade;

import cz.muni.fi.airportapi.dto.DestinationCreationalDTO;
import cz.muni.fi.airportapi.dto.DestinationDTO;
import cz.muni.fi.airportapi.dto.UpdateDestinationLocationDTO;
import cz.muni.fi.airportapi.facade.DestinationFacade;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Zbranek
 */
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class DestinationFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private DestinationFacade destinationFacade;
    private DestinationCreationalDTO creationalDestinationDTO1;
    private DestinationCreationalDTO creationalDestinationDTO2;
    private DestinationDTO destinationDTO;
    
    @BeforeMethod
    public void setup() {
        creationalDestinationDTO1 = new DestinationCreationalDTO();
        creationalDestinationDTO1.setLocation("Phobos");
        
        creationalDestinationDTO2 = new DestinationCreationalDTO();
        creationalDestinationDTO2.setLocation("Deimos");
        
        destinationDTO = new DestinationDTO();
        destinationDTO.setLocation("Phobos");
    }
    
    @Test
    public void testGetDestinationWithId() {
        DestinationDTO dDTO = destinationFacade.getDestinationWithId(destinationFacade.createDestination(creationalDestinationDTO1));
        Assert.assertEquals(dDTO, destinationDTO);
    }

    @Test
    public void testGetAllDestinations() {
        destinationFacade.createDestination(creationalDestinationDTO1);
        List<DestinationDTO> l1 = destinationFacade.getAllDestinations();
        List<DestinationDTO> l2 = new ArrayList<>();
        l2.add(destinationDTO);
        Assert.assertEquals(l1, l2);
    }

    @Test
    public void testGetDestinationWithLocation() {
        destinationFacade.createDestination(creationalDestinationDTO1);
        List<DestinationDTO> l1 = destinationFacade.getDestinationWithLocation("Phobos");
        List<DestinationDTO> l2 = new ArrayList<>();
        l2.add(destinationDTO);
        Assert.assertEquals(l1, l2);
    }

    @Test
    public void testCreateDestination() {
        Long id = destinationFacade.createDestination(creationalDestinationDTO1);
        Assert.assertNotNull(id);
    }

    @Test
    public void testRemoveDestination() {
        Long id = destinationFacade.createDestination(creationalDestinationDTO1);
        destinationFacade.removeDestination(id);
        List<DestinationDTO> l = destinationFacade.getAllDestinations();
        assert l.isEmpty();
    }

    @Test
    public void testUpdateDestinationLocation() {
        Long id = destinationFacade.createDestination(creationalDestinationDTO1);
        
        UpdateDestinationLocationDTO update = new UpdateDestinationLocationDTO();
        update.setLocation(creationalDestinationDTO1.getLocation());
        update.setId(id);
        
        DestinationDTO destination = destinationFacade.getDestinationWithId(id);
        //System.out.println(destinationFacade.getDestinationWithId(id).getLocation());
        assert destination.getLocation().equals(creationalDestinationDTO1.getLocation());
        
        destinationFacade.updateDestinationLocation(update);
        
        destination = destinationFacade.getDestinationWithId(id); 
        assert destination.getLocation().equals(creationalDestinationDTO1.getLocation());
    }
}
