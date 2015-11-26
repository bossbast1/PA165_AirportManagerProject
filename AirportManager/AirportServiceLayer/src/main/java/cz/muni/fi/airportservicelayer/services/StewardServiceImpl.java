/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.dao.StewardDao;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airportservicelayer.exceptions.BasicDataAccessException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastian Kupka
 */
@Service
public class StewardServiceImpl implements StewardService {
    
    @Inject
    private StewardDao stewardDao;
    
    @Inject
    private DestinationDao destinationDao;

    @Override
    public Steward findById(Long id) {
        return stewardDao.findById(id);
    }

    @Override
    public Steward findByPersonalIdentificator(String personalIdentificator) {
        return stewardDao.findByIdentificator(personalIdentificator);
    }

    @Override
    public List<Steward> getAllStewards() {
        return stewardDao.findAll();
    }

    @Override
    public List<Steward> findByName(String name, String surname) {
        return stewardDao.findByName(name, surname);
    }

    @Override
    public Long createSteward(Steward steward) {
        stewardDao.create(steward);
        return steward.getId();
    }

    @Override
    public void removeSteward(Long id) {
        stewardDao.remove(id);
    }

    @Override
    public void updateSteward(Steward update) {
        stewardDao.update(update);
    }

    @Override
    public List<Flight> getStewardFlights(Long id) {
        return this.findStewardFlights(stewardDao.findById(id));
    } 
    
    @Override
    public List<Steward> findAvailableStewards(Date fromDate, Date toDate) {
        return stewardDao.findAvailableStewards(fromDate, toDate);
    }
    
    @Override
    public List<Flight> findStewardFlights(Steward steward) {
        return stewardDao.findLastStewardFlights(steward);
    }

    //Advanced service
    @Override
    public List<Steward> findSpecificStewards(Date fromDate, Date toDate, Long locationId) {
        List<Steward> availableStewards = null;
        if (fromDate != null || toDate != null) {
            if (fromDate == null) {
                fromDate = new Date();
            }
            
            if (toDate == null) {
                toDate = new Date();
            }
            availableStewards = this.findAvailableStewards(fromDate, toDate);
        } else {
            availableStewards = this.getAllStewards();
        }
        if (locationId != null) {
            return findSpecificStewards(availableStewards, locationId);
        } else {
            return availableStewards;
        }
    }
    
    @Override
    public List<Steward> findAvailableStewardsAtLocation(long locationId) {
        return this.findSpecificStewards(null, null, locationId);
    }

    private List<Steward> findSpecificStewards(List<Steward> availableStewards, long locationId) {
        List<Steward> specificAirplanes = new ArrayList<>();
        for(Steward steward : availableStewards) {
            if (new Long(locationId).equals(stewardDao.findLastStewardFlights(steward).get(0).getDestination().getId())) {
                specificAirplanes.add(steward);
            }
        }
        return specificAirplanes;
    }
}
