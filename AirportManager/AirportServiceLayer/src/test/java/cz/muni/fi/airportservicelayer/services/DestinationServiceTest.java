/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import cz.muni.fi.airportservicelayer.exceptions.BasicDataAccessException;
import java.util.ArrayList;
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
 * @author Michal Zbranek
 */
@ContextConfiguration(classes=ServiceTestConfiguration.class)
public class DestinationServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private DestinationDao destinationDao;
    
    @InjectMocks
    @Autowired
    private DestinationService destinationService;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    private Destination destination1;
    private Destination destination2;
    
    @BeforeMethod
    public void prepareTestData() {
        destination1 = new Destination();
        destination1.setId(1L);
        destination1.setLocation("Phobos");
        
        destination2 = new Destination();
        destination2.setId(2L);
        destination2.setLocation("Deimos");
    }
    
    @Test
    public void testFindById() {
        when(destinationDao.findById(1L)).thenReturn(destination1);
        Assert.assertEquals(destinationService.findById(1L), destination1);
    }
    
    @Test
    public void testFindByName() {
        List l = new ArrayList<>();
        l.add(destination1);
        when(destinationDao.findByLocation("Phobos")).thenReturn(l);
        Assert.assertEquals(destinationService.findByLocation("Phobos"), l);
    }
    
    @Test
    public void testFindAll() {
        List l = new ArrayList<>();
        l.add(destination1);
        l.add(destination2);
        when(destinationDao.findAll()).thenReturn(l);
        Assert.assertEquals(destinationService.findAllDestinations(), l);
    }
    
    @Test(expectedExceptions = BasicDataAccessException.class)
    public void testThrowBasicDataAccesException() {
        when(destinationDao.findAll()).thenThrow(new PersistenceException());
        destinationService.findAllDestinations();
    }  
    
}
