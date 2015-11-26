/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import cz.muni.fi.airportservicelayer.exceptions.BasicDataAccessException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Stromský
 */  
@ContextConfiguration(classes=ServiceTestConfiguration.class)
public class AirplaneServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private AirplaneDao airplaneDao;
    
    @InjectMocks
    @Autowired
    private AirplaneService airplaneService;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Airplane airplane1;
    private Airplane airplane2;
    
    @BeforeMethod
    public void prepareTestData() {
        airplane1 = new Airplane();
        airplane1.setId(1L);
        airplane1.setCapacity(130);
        airplane1.setName("Phobos");
        airplane1.setType("JS-130");
        
        airplane2 = new Airplane();
        airplane2.setId(2L);
        airplane2.setCapacity(25);
        airplane2.setName("Deimos");
        airplane2.setType("JS-25");
    }
    
    @Test
    public void testFindById() {
        when(airplaneDao.findById(1L)).thenReturn(airplane1);
        Assert.assertEquals(airplaneService.findById(1L), airplane1);
    }
    
    @Test
    public void testFindByName() {
        List l = new ArrayList<>();
        l.add(airplane1);
        when(airplaneDao.findByName("Phobos")).thenReturn(l);
        Assert.assertEquals(airplaneService.findByName("Phobos"), l);
    }
    
    @Test
    public void testFindAll() {
        List l = new ArrayList<>();
        l.add(airplane1);
        l.add(airplane2);
        when(airplaneDao.findAllAirplanes()).thenReturn(l);
        Assert.assertEquals(airplaneService.findAllAirplanes(), l);
    }
    
    @Test
    public void testFindAllAvailable() {
        List l = new ArrayList<>();
        l.add(airplane1);
        
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 1, 8, 0, 0);
        Date from = cal.getTime();
        cal.set(2015, 1, 15, 0, 0);
        Date to = cal.getTime();
        
        when(airplaneDao.findAvailableAirplanes(from, to)).thenReturn(l);
        Assert.assertEquals(airplaneService.findAvailableAirplanes(from, to), l);        
    }
    
    @Test
    public void testFindAirplaneFlights() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 1, 8, 0, 0);
        Date departure = cal.getTime();
        cal.set(2015, 1, 9, 0, 0);
        Date arrival = cal.getTime();
        
        Flight flight = new Flight();
        flight.setAirplane(airplane1);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setOrigin(new Destination());
        
        List flights = new ArrayList<>();
        flights.add(flight);
        
        when(airplaneDao.findAirplaneFlights(airplane1)).thenReturn(flights);
        Assert.assertEquals(airplaneService.findAirplaneFlights(airplane1), flights);
    }
    
    @Test
    public void testFindSpecificAirplanes() {
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 1, 8, 0, 0);
        Date from = cal.getTime();
        cal.set(2015, 1, 15, 0, 0);
        Date to = cal.getTime();
        
        List l = new ArrayList<>();
        l.add(airplane1);
        when(airplaneDao.findAvailableAirplanes(from, to)).thenReturn(l);
        
        cal.set(2015, 1, 8, 0, 0);
        Date departure = cal.getTime();
        cal.set(2015, 1, 9, 0, 0);
        Date arrival = cal.getTime();
        
        Flight flight = new Flight();
        flight.setAirplane(airplane1);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        Destination d = new Destination();
        d.setLocation("Brno");
        flight.setDestination(d);
        flight.setOrigin(d);
        
        List flights = new ArrayList<>();
        flights.add(flight);
        
        when(airplaneDao.findAirplaneFlights(airplane1)).thenReturn(flights);
        Assert.assertEquals(airplaneService.findSpecificAirplanes(from, to, 100, "Brno"), l);
    }
    
    @Test(expectedExceptions = BasicDataAccessException.class)
    public void testThrowBasicDataAccesException() {
        when(airplaneDao.findAllAirplanes()).thenThrow(new PersistenceException());
        airplaneService.findAllAirplanes();
    } 
}
