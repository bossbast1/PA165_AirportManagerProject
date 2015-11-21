/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.entity.Airplane;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jakub Stromský
 */
@Service
public class AirplaneServiceImpl implements AirplaneService {
    
    @Inject
    private AirplaneDao airplaneDao;
   
    //DAO delegations
    @Override
    public Airplane findById(Long id) {
        return airplaneDao.findById(id);
    }
    
    @Override
    public List<Airplane> findAllAirplanes() {
        return airplaneDao.findAllAirplanes();
    }
    
    @Override
    public List<Airplane> findByName(String name) {
        return airplaneDao.findByName(name);
    }
    
    @Override
    public void create(Airplane airplane) {
        airplaneDao.create(airplane);
    }
    
    @Override
    public void remove(Airplane airplane) {
        airplaneDao.delete(airplane);
    }
    
    @Override
    public void update(Airplane airplane) {
        airplaneDao.update(airplane);
    }
    
    @Override
    public List<Airplane> findAvailableAirplanes(Date fromDate, Date toDate) {
        List<Airplane> availableAirplanes = airplaneDao.findAvailableAirplanes(fromDate, toDate);
        return availableAirplanes;
    }

    //Advanced service
    @Override
    public List<Airplane> findSpecificAirplanes(Date fromDate, Date toDate, int capacity, String location) {
        List<Airplane> availableAirplanes = airplaneDao.findAvailableAirplanes(fromDate, toDate);
        List<Airplane> specificAirplanes = new ArrayList<>();
        for(Airplane airplane : availableAirplanes) {
            if (airplane.getCapacity() >= capacity
                    && location.equals(airplaneDao.getLastAirplaneFlight(airplane).getDestination().getLocation())) {
                specificAirplanes.add(airplane);
            }
        }
        return specificAirplanes;
    }    
}
