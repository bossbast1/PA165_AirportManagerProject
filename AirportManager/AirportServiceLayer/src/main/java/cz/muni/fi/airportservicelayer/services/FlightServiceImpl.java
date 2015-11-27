/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.FlightDao;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airportservicelayer.exceptions.IllegalArgumentDataException;
import cz.muni.fi.airportservicelayer.exceptions.BasicDataAccessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabrila Podolnikova
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;
    
    //delegation
    @Override
    public void create(Flight f) {
        try {
            flightDao.create(f);
        } catch (Exception e) {
            throw new BasicDataAccessException("Exception on create in FlightDao", e);   
        }
    }

    @Override
    public void delete(Flight f) {
        try {
            flightDao.delete(f);
        } catch (Exception e) {
            throw new BasicDataAccessException("Exception on delete in FlightDao", e);
        }
    }
    
    @Override
    public void update(Flight f) {
        try {
            flightDao.update(f);
        } catch (Exception e) {
            throw new BasicDataAccessException("Exception on update in FlightDao", e);
        }
    }

    @Override
    public Flight findById(Long id) {
        try {
            return flightDao.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentDataException("Exception on findById in FlightDao", e);
        }
    }

    @Override
    public List<Flight> findAll() {
        try {
            return flightDao.findAll();
        } catch (Exception e) {
            throw new IllegalArgumentDataException("Exception on findAll in FlightDao", e);
        }
    }

    @Override
    public List<Flight> listByDate(boolean arrival) {
        try {
            return flightDao.listByDate(arrival);
        }catch (Exception e) {
            throw new IllegalArgumentDataException("Exception on listByDate in FlightDao", e);
        }
    }

    @Override
    public List<Flight> listByOrigin(Destination origin) {
        try {
            return flightDao.listByOrigin(origin);
        }catch (Exception e) {
            throw new IllegalArgumentDataException("Exception on listByOrigin in FlightDao", e);
        }
    }

    @Override
    public List<Flight> listByDestination(Destination destination) {
        try {
            return flightDao.listByDestination(destination);
        }catch (Exception e) {
            throw new IllegalArgumentDataException("Exception on listByDestination in FlightDao", e);
        }
    }
    
}
