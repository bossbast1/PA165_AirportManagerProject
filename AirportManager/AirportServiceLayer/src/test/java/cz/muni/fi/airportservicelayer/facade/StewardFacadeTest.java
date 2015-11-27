/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.facade;

import cz.muni.fi.airport.JpaTestContext;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airport.enums.Gender;
import cz.muni.fi.airportapi.dto.FlightDTO;
import cz.muni.fi.airportapi.dto.StewardCreationalDTO;
import cz.muni.fi.airportapi.dto.StewardDTO;
import cz.muni.fi.airportapi.dto.UpdateStewardNameDTO;
import cz.muni.fi.airportapi.facade.DestinationFacade;
import cz.muni.fi.airportapi.facade.FlightFacade;
import cz.muni.fi.airportapi.facade.StewardFacade;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Sebastian Kupka
 */ 
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class StewardFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private StewardFacade stewardFacade;
    
    private FlightFacade flightFacade;
    private DestinationFacade destinationFacade;
    
    private StewardCreationalDTO s1;
    private StewardCreationalDTO s2;
    SimpleDateFormat formatter;
    
    @BeforeMethod
    public void setUp() {
        s1 = new StewardCreationalDTO();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        s2 = new StewardCreationalDTO();
        Date birth2 = null;
        Date employment2 = null;
        try {
            birth2 = formatter.parse("1985/02/08");
            employment2 = formatter.parse("2013/04/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardFacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s2.setDateOfBirth(birth2);
        s2.setEmploymentDate(employment2);
        s2.setFirstname("Peter");
        s2.setSurname("Malick");
        s2.setGender(Gender.MALE);
        s2.setPersonalIdentificator("123-23456");
    }

    @Test
    public void testGetStewardWithId() {
        StewardDTO steward= stewardFacade.getStewardWithId(stewardFacade.createSteward(s1));
        Assert.notNull(steward);
        assert steward.getPersonalIdentificator().equals(s1.getPersonalIdentificator());
        
    }

    @Test
    public void testGetStewardWithPersonalIdentificator() {
        StewardDTO steward = stewardFacade.getStewardWithId(stewardFacade.createSteward(s1));
        StewardDTO result = stewardFacade.getStewardWithPersonalIdentificator(s1.getPersonalIdentificator());
        assertEquals(steward, result);
        
    }

    @Test
    public void testGetAllStewards() {
        List<StewardDTO> expResult = new ArrayList<>();
        expResult.add(stewardFacade.getStewardWithId(stewardFacade.createSteward(s1)));
        expResult.add(stewardFacade.getStewardWithId(stewardFacade.createSteward(s2)));
        List<StewardDTO> result = stewardFacade.getAllStewards();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGetStewardWithName() {
        s1.setFirstname("None");
        s2.setFirstname("FirstName");
        s2.setSurname("Surname");
        stewardFacade.createSteward(s1);
        StewardDTO steward = stewardFacade.getStewardWithId(stewardFacade.createSteward(s2));
        List<StewardDTO> result = stewardFacade.getStewardWithName("FirstName", "Surname");
        assert result.size() == 1;
        assert result.get(0).getFirstname().equals(steward.getFirstname());
        assert result.get(0).getSurname().equals(steward.getSurname());
    }

    @Test
    public void testCreateSteward() {
        Long id = stewardFacade.createSteward(s1);
        StewardDTO steward = stewardFacade.getStewardWithId(id);
        Assert.notNull(steward);
        assert steward.getPersonalIdentificator().equals(s1.getPersonalIdentificator()); 
    }

    @Test
    public void testRemoveSteward() {
        Long id = stewardFacade.createSteward(s1);
        Assert.notNull(stewardFacade.getStewardWithId(id));
        stewardFacade.removeSteward(id);
        StewardDTO tmp = stewardFacade.getStewardWithId(id);
        Assert.isNull(tmp);
        
    }

    @Test
    public void testUpdateStewardName() {
        Long id = stewardFacade.createSteward(s1);
        
        UpdateStewardNameDTO update = new UpdateStewardNameDTO();
        update.setFirstname(s1.getFirstname() + " New");
        update.setSurname(s1.getSurname()+ " New");
        update.setId(id);
        
        StewardDTO steward = stewardFacade.getStewardWithId(id); 
        assert steward.getFirstname().equals(s1.getFirstname());
        assert steward.getSurname().equals(s1.getSurname());
        
        stewardFacade.updateStewardName(update);
        
        steward = stewardFacade.getStewardWithId(id); 
        assert steward.getFirstname().equals(s1.getFirstname() + " New");
        assert steward.getSurname().equals(s1.getSurname()+ " New");   
    }

    @Test
    public void testGetAvailableStewardsAtLocation() {
        // TODO
    }

    @Test
    public void testGetStewardFlights() {
        // TODO
    }
    
    @Test
    public void testFindSpecificStewards() {
        // TODO
    }
} 