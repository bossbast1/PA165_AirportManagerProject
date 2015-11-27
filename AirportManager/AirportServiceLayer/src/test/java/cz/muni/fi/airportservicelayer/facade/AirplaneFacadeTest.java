/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.facade;

import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.dao.FlightDao;
import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airportapi.dto.AirplaneCreationalDTO;
import cz.muni.fi.airportapi.dto.AirplaneDTO;
import cz.muni.fi.airportapi.dto.UpdateAirplaneCapacityDTO;
import cz.muni.fi.airportapi.facade.AirplaneFacade;
import cz.muni.fi.airportapi.facade.FlightFacade;
import cz.muni.fi.airportapi.facade.StewardFacade;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Kuba
 */
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class AirplaneFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private AirplaneFacade airplaneFacade;
    
    //@Autowired
    private FlightFacade flightFacade;
    
    private AirplaneCreationalDTO creationalAirplaneDTO1;
    private AirplaneCreationalDTO creationalAirplaneDTO2;
    private AirplaneDTO airplaneDTO;
    
    @BeforeMethod
    public void setup() {
        creationalAirplaneDTO1 = new AirplaneCreationalDTO();
        creationalAirplaneDTO1.setCapacity(100);
        creationalAirplaneDTO1.setName("Phobos");
        creationalAirplaneDTO1.setType("JS-100");
        
        creationalAirplaneDTO2 = new AirplaneCreationalDTO();
        creationalAirplaneDTO2.setCapacity(50);
        creationalAirplaneDTO2.setName("Deimos");
        creationalAirplaneDTO2.setType("JS-50");
        
        airplaneDTO = new AirplaneDTO();
        airplaneDTO.setName("Phobos");
        airplaneDTO.setType("JS-100");
        airplaneDTO.setCapacity(100);
        
        
    }
    
    @Test
    public void testGetAirplaneWithId() {
        AirplaneDTO aDTO = airplaneFacade.getAirplaneWithId(airplaneFacade.createAirplane(creationalAirplaneDTO1));
        Assert.assertEquals(aDTO, airplaneDTO);
    }

    @Test
    public void testGetAllAirplanes() {
        airplaneFacade.createAirplane(creationalAirplaneDTO1);
        List<AirplaneDTO> l1 = airplaneFacade.getAllAirplanes();
        List<AirplaneDTO> l2 = new ArrayList<>();
        l2.add(airplaneDTO);
        Assert.assertEquals(l1, l2);
    }

    @Test
    public void testGetAirplaneWithName() {
        airplaneFacade.createAirplane(creationalAirplaneDTO1);
        List<AirplaneDTO> l1 = airplaneFacade.getAirplaneWithName("Phobos");
        List<AirplaneDTO> l2 = new ArrayList<>();
        l2.add(airplaneDTO);
        Assert.assertEquals(l1, l2);
    }

    @Test
    public void testCreateAirplane() {
        Long id = airplaneFacade.createAirplane(creationalAirplaneDTO1);
        Assert.assertNotNull(id);
    }

    @Test
    public void testRemoveAirplane() {
        Long id = airplaneFacade.createAirplane(creationalAirplaneDTO1);
        airplaneFacade.removeAirplane(id);
        List<AirplaneDTO> l = airplaneFacade.getAllAirplanes();
        assert l.isEmpty();
    }

    @Test
    public void testUpdateAirplaneCapacity() {
        Long id = airplaneFacade.createAirplane(creationalAirplaneDTO1);
        UpdateAirplaneCapacityDTO update = new UpdateAirplaneCapacityDTO();
        update.setAirplaneId(id);
        update.setCapacity(120);
        airplaneFacade.updateAirplaneCapacity(update);
        assert 120 == airplaneFacade.getAirplaneWithId(id).getCapacity();
    }

    @Test
    public void testGetAvailableAirplanes() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 1, 8, 0, 0);
        Date from = cal.getTime();
        cal.set(2015, 1, 15, 0, 0);
        Date to = cal.getTime();
        
        Long id = airplaneFacade.createAirplane(creationalAirplaneDTO1);
        List<AirplaneDTO> l1 = airplaneFacade.getAvailableAirplanes(from, to);
        List<AirplaneDTO> l2 = new ArrayList<>();
        l2.add(airplaneDTO);
        Assert.assertEquals(l1, l2);
    }

    @Test
    public void testGetAirplaneFlights(Long id) {       
        //Not supported because flight facade is not implemented (27.11. - 01:00:00
        //TODO
    }

    @Test
    public void testGetSpecificAirplanes(Date from, Date to, int capacity, String location) {
        //Not supported because flight facade is not implemented (27.11. - 01:00:00
        //TODO
    }
}