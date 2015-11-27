/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.facade;

import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.dao.StewardDao;
import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airport.enums.Gender;
import cz.muni.fi.airportapi.dto.FlightCreationalDTO;
import cz.muni.fi.airportapi.dto.FlightDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightArrivalDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightDepartureDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightDestinationDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightOriginDTO;
import cz.muni.fi.airportapi.dto.UpdateFlightsAirplaneDTO;
import cz.muni.fi.airportapi.facade.FlightFacade;
import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Gabi
 */
@ContextConfiguration(classes = {ServiceTestConfiguration.class})
public class FlightFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private FlightFacade flightFacade;
    
    @Autowired
    public StewardDao stewardDao;

    @Autowired
    public DestinationDao destinationDao;

    @Autowired
    public AirplaneDao airplaneDao;

    private FlightCreationalDTO flightCreationalDTO1;
    private FlightCreationalDTO flightCreationalDTO2;

    private FlightDTO flightDTO;

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

    @BeforeMethod
    public void setup() throws ParseException {
        
        flightCreationalDTO1 = new FlightCreationalDTO();

        flightCreationalDTO2 = new FlightCreationalDTO();
        
        flightDTO = new FlightDTO();

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

        airplaneDao.create(a1);
        airplaneDao.create(a2);

        d1 = new Destination();
        d1.setLocation("Brno");

        d2 = new Destination();
        d2.setLocation("Praha");

        destinationDao.create(d1);
        destinationDao.create(d2);

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

        stewardDao.create(s1);
        stewardDao.create(s2);
        stewardDao.create(s3);
        
        flightCreationalDTO1.setAirplane(a1);
        flightCreationalDTO1.setArrival(date2);
        flightCreationalDTO1.setDeparture(date1);
        flightCreationalDTO1.setOrigin(d1);
        flightCreationalDTO1.setDestination(d2);
        flightCreationalDTO1.addSteward(s1);
        flightCreationalDTO1.addSteward(s2);
        
        flightCreationalDTO2.setAirplane(a2);
        flightCreationalDTO2.setArrival(date3);
        flightCreationalDTO2.setDeparture(date2);
        flightCreationalDTO2.setOrigin(d2);
        flightCreationalDTO2.setDestination(d1);
        flightCreationalDTO2.addSteward(s1);
        
        flightDTO.setAirplane(a1);
        flightDTO.setArrival(date2);
        flightDTO.setDeparture(date1);
        flightDTO.setOrigin(d1);
        flightDTO.setDestination(d2);
        flightDTO.addSteward(s1);
        flightDTO.addSteward(s2);
    }
    
    @Test
    public void testGetFlightWithId() {
        FlightDTO f= flightFacade.getFlightWithId(flightFacade.createFlight(flightCreationalDTO1));
        assert f.getArrival().equals(flightCreationalDTO1.getArrival());
        assert f.getAirplane().equals(flightCreationalDTO1.getAirplane());
    }
    
//    public List<FlightDTO> getAllFlights();
    @Test
    public void testGetAllFlights(){
        flightFacade.createFlight(flightCreationalDTO1);
        List<FlightDTO> l1 = flightFacade.getAllFlights();
        assert l1.get(0).getArrival().equals(flightCreationalDTO1.getArrival());
        assert l1.get(0).getAirplane().equals(flightCreationalDTO1.getAirplane());
    }
    
//    public List<FlightDTO> getFlightsByDate(boolean arrival);
    @Test
    public void testGetFlightsByDate() {
        flightFacade.createFlight(flightCreationalDTO1);
        flightFacade.createFlight(flightCreationalDTO2);
        //flight should be ordered by arrival
        List<FlightDTO> l1 = flightFacade.getFlightsByDate(true);
        assert l1.get(0).getArrival().equals(flightCreationalDTO1.getArrival());
        assert l1.get(1).getArrival().equals(flightCreationalDTO2.getArrival());
    }
    
    @Test
    public void testCreateFlight() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        Assert.assertNotNull(id);
    }
//    public void removeFlight(Long id);
    @Test
    public void testRemoveFlight() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        flightFacade.removeFlight(id);
        List<FlightDTO> l = flightFacade.getAllFlights();
        assert l.isEmpty();
    }
//    public void updateFlightArrival(UpdateFlightArrivalDTO update);
    @Test
    public void testUpdateFlightArrival() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        UpdateFlightArrivalDTO update = new UpdateFlightArrivalDTO();
        update.setId(id);
        update.setArrival(date3);
        flightFacade.updateFlightArrival(update);
        assert date3.equals(flightFacade.getFlightWithId(id).getArrival());
    }
//    public void updateFlightDeparture(UpdateFlightDepartureDTO update);
    @Test
    public void testUpdateFlightDeparture() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        UpdateFlightDepartureDTO update = new UpdateFlightDepartureDTO();
        update.setId(id);
        update.setDeparture(date3);
        flightFacade.updateFlightDeparture(update);
        assert date3.equals(flightFacade.getFlightWithId(id).getDeparture());
    }
//    public void updateFlightDestination(UpdateFlightDestinationDTO update);
    @Test
    public void testUpdateFlightDestination() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        UpdateFlightDestinationDTO update = new UpdateFlightDestinationDTO();
        update.setId(id);
        update.setDestination(d1);
        flightFacade.updateFlightDestination(update);
        assert d1.equals(flightFacade.getFlightWithId(id).getDestination());
    }
//    public void updateFlightOrigin(UpdateFlightOriginDTO update);
    @Test
    public void testUpdateFlightOrigin() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        UpdateFlightOriginDTO update = new UpdateFlightOriginDTO();
        update.setId(id);
        update.setOrigin(d2);
        flightFacade.updateFlightOrigin(update);
        assert d2.equals(flightFacade.getFlightWithId(id).getOrigin());
    }
//    public void updateFlightAirplane(UpdateFlightsAirplaneDTO update);
    @Test
    public void testUpdateFlightAirplane() {
        Long id = flightFacade.createFlight(flightCreationalDTO1);
        UpdateFlightsAirplaneDTO update = new UpdateFlightsAirplaneDTO();
        update.setId(id);
        update.setAirplane(a2);
        flightFacade.updateFlightAirplane(update);
        assert a2.equals(flightFacade.getFlightWithId(id).getAirplane());
    }
//    public List<FlightDTO> getFlightsByOrigin(Destination origin);
    @Test
    public void testGetFlightsByOrigin() {
        flightFacade.createFlight(flightCreationalDTO1);
        List<FlightDTO> l1 = flightFacade.getFlightsByOrigin(d1);
        assert l1.get(0).getOrigin().equals(d1);
    }
//    public List<FlightDTO> getFlightsByDestination(Destination destination);
    @Test
    public void testGetFlightsByDestination() {
        flightFacade.createFlight(flightCreationalDTO1);
        List<FlightDTO> l1 = flightFacade.getFlightsByDestination(d2);
        assert l1.get(0).getDestination().equals(d2);
    }
}
