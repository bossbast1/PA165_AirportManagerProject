/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.dao.FlightDao;
import cz.muni.fi.airport.dao.StewardDao;
import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airport.enums.Gender;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
 * @author Gabi
 */
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public class FlightServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private FlightDao flightDao;

    @InjectMocks
    @Autowired
    private FlightService flightService;

    private Flight f1;
    private Flight f2;

    private Steward s1;
    private Steward s2;
    private Steward s3;
    private Destination d1;
    private Destination d2;
    private Airplane a1;
    private Airplane a2;

    private Date date1;
    private Date date2;
    private Date date3;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void prepareTestData() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 1, 1, 2, 0);
        date1 = cal.getTime();
        cal.set(2015, 1, 1, 12, 30);
        date2 = cal.getTime();
        cal.set(2015, 1, 2, 0, 0);
        date3 = cal.getTime();

        a1 = new Airplane();
        a1.setCapacity(1);
        a1.setName("airA");
        a1.setType("typeA");

        a2 = new Airplane();
        a2.setCapacity(2);
        a2.setName("airB");
        a2.setType("typeB");

        d1 = new Destination();
        d1.setLocation("Brno");

        d2 = new Destination();
        d2.setLocation("Praha");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        s1 = new Steward();
        s1.setFirstname("fn1");
        s1.setSurname("sn1");
        s1.setPersonalIdentificator("111-11111");
        s1.setDateOfBirth(formatter.parse("1988/02/02"));
        s1.setEmploymentDate(formatter.parse("2014/03/01"));
        s1.setGender(Gender.MALE);

        s2 = new Steward();
        s2.setFirstname("fn2");
        s2.setSurname("sn1");
        s2.setPersonalIdentificator("111-11112");
        s2.setDateOfBirth(formatter.parse("1988/02/02"));
        s2.setEmploymentDate(formatter.parse("2014/03/01"));
        s2.setGender(Gender.MALE);

        s3 = new Steward();
        s3.setFirstname("fn2");
        s3.setSurname("sn3");
        s3.setPersonalIdentificator("111-11113");
        s3.setDateOfBirth(formatter.parse("1988/02/02"));
        s3.setEmploymentDate(formatter.parse("2014/03/01"));
        s3.setGender(Gender.FEMALE);

        f1 = new Flight();
        f1.setId(1L);
        f1.setAirplane(a1);
        f1.setArrival(date2);
        f1.setDeparture(date1);
        f1.setOrigin(d1);
        f1.setDestination(d2);
        f1.addSteward(s1);
        f1.addSteward(s2);
        
        f2 = new Flight();
        f2.setId(2L);
        f2.setAirplane(a2);
        f2.setArrival(date3);
        f2.setDeparture(date2);
        f2.setOrigin(d2);
        f2.setDestination(d1);
        f2.addSteward(s1); 
    }
    
    @Test
    public void testFindById() {
        when(flightDao.findById(1L)).thenReturn(f1);
        Assert.assertEquals(flightService.findById(1L), f1);
    }
    
    @Test
    public void testFindAll() {
        List l = new ArrayList<>();
        l.add(f1);
        l.add(f2);
        when(flightDao.findAll()).thenReturn(l);
        Assert.assertEquals(flightService.findAll(), l);
    }
    
    @Test
    public void listByDate() {
        List l = new ArrayList<>();
        l.add(f1);
        l.add(f2);
        when(flightDao.listByDate(true)).thenReturn(l);
        Assert.assertEquals(flightService.listByDate(true).get(0), f1);
        Assert.assertEquals(flightService.listByDate(true).get(1), f2);
    }
    
    @Test
    public void listByOrigin() {
        List l = new ArrayList<>();
        l.add(f1);
        when(flightDao.listByOrigin(d1.getId())).thenReturn(l);
        Assert.assertEquals(flightService.listByOrigin(d1.getId()), l);
    }
    
    @Test
    public void listByDestination() {
        List l = new ArrayList<>();
        l.add(f1);
        when(flightDao.listByDestination(d2.getId())).thenReturn(l);
        Assert.assertEquals(flightService.listByDestination(d2.getId()), l);
    }
}
