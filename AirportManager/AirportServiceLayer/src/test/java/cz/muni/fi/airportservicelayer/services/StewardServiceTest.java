/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.StewardDao;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airport.enums.Gender;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.service.spi.ServiceException;

import org.mockito.InjectMocks;
import org.mockito.*;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.util.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sebastian Kupka
 */ 
@ContextConfiguration(classes=ServiceTestConfiguration.class)
public class StewardServiceTest extends AbstractTransactionalTestNGSpringContextTests  
{ 
    @Mock
    private StewardDao stewardDao;
    
    @Autowired
    @InjectMocks
    private StewardService stewardService;
    
    private Steward s1;
    private Steward s2;
    SimpleDateFormat formatter;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepare() {
        s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        s1.setId(1l);
        
        s2 = new Steward();
        Date birth2 = null;
        Date employment2 = null;
        try {
            birth2 = formatter.parse("1985/02/08");
            employment2 = formatter.parse("2013/04/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s2.setDateOfBirth(birth2);
        s2.setEmploymentDate(employment2);
        s2.setFirstname("Peter");
        s2.setSurname("Malick");
        s2.setGender(Gender.MALE);
        s2.setPersonalIdentificator("123-23456");
        s2.setId(2l);
        
        List<Steward> stewards = new ArrayList<Steward>();
        stewards.add(s1);
        stewards.add(s2);
        
        when(stewardDao.findAll()).thenReturn(stewards);
        
        when(stewardDao.findById(2l)).thenReturn(s2);
        when(stewardDao.findById(1l)).thenReturn(s1);
        
        when(stewardDao.findByIdentificator("123-23456")).thenReturn(s2);
        when(stewardDao.findByIdentificator("123-12345")).thenReturn(s1);
        
        stewards = new ArrayList<Steward>();
        stewards.add(s1);
        
        when(stewardDao.findByName(s1.getFirstname(), s1.getSurname())).thenReturn(stewards);
        
        
        stewards = new ArrayList<Steward>();
        stewards.add(s2);
        
        when(stewardDao.findByName(s2.getFirstname(), s2.getSurname())).thenReturn(stewards);
    }

    @Test
    public void testFindById() {
        Steward steward= stewardService.findById(stewardService.createSteward(s1));
        Assert.notNull(steward);
        assert steward.getPersonalIdentificator().equals(s1.getPersonalIdentificator());
    }

    @Test
    public void testFindByPersonalIdentificator() {
        Steward steward = stewardService.findById(stewardService.createSteward(s1));
        Steward result = stewardService.findByPersonalIdentificator(s1.getPersonalIdentificator());
        assertEquals(steward, result);
    }

    @Test
    public void testGetAllStewards() {
        List<Steward> expResult = new ArrayList<>();
        expResult.add(stewardService.findById(stewardService.createSteward(s1)));
        expResult.add(stewardService.findById(stewardService.createSteward(s2)));
        List<Steward> result = stewardService.getAllStewards();
        assertEquals(expResult, result);
    }

    @Test
    public void testFindByName() {
        Steward steward = stewardService.findById(stewardService.createSteward(s1));
        List<Steward> result = stewardService.findByName(steward.getFirstname(), steward.getSurname());
        assert result.size() == 1;
        assert result.get(0).getFirstname().equals(steward.getFirstname());
        assert result.get(0).getSurname().equals(steward.getSurname());
        
        steward = stewardService.findById(stewardService.createSteward(s2));
        result = stewardService.findByName(steward.getFirstname(), steward.getSurname());
        assert result.size() == 1;
        assert result.get(0).getFirstname().equals(steward.getFirstname());
        assert result.get(0).getSurname().equals(steward.getSurname());
    }

    @Test
    public void testGetStewardFlights() {
    }

    @Test
    public void testFindAvailableStewards() {
    }

    @Test
    public void testFindStewardFlights() {
    }

    @Test
    public void testFindSpecificStewards() {
    }

    @Test
    public void testFindAvailableStewardsAtLocation() {
    }
} 
