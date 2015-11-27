/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.entity.Destination;
import cz.muni.fi.airportservicelayer.exceptions.BasicDataAccessException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michal Zbranek
 */
@Service
public class DestinationServiceImpl implements DestinationService{

    @Autowired
    private DestinationDao destinationDao;
    
    @Override
    public Destination findById(Long id) {
        try{
            return destinationDao.findById(id);
        } catch (Exception ex) {
            throw new BasicDataAccessException("Exception on findById in DestinationDao", ex);
        }
    }

    @Override
    public List<Destination> findAllDestinations() {
        try {
            return destinationDao.findAll();
        } catch (Exception ex) {
            throw new BasicDataAccessException("Exception on findAll"
                    + " in Destination Dao", ex);
        }
    }

    @Override
    public List<Destination> findByLocation(String location) {
        try {
            return destinationDao.findByLocation(location);
        } catch (Exception ex) {
            throw new BasicDataAccessException("Exception on findByLocation in DestinationDao", ex);
        }
    }

    @Override
    public void create(Destination destination) {
        try { 
            destinationDao.create(destination);
        } catch (Exception ex) {
            throw new BasicDataAccessException("Exception on create in DestinationDao", ex);
        }
    }

    @Override
    public void remove(Destination destination) {
        try {
            destinationDao.remove(destination);
        } catch (Exception ex) {
            throw new BasicDataAccessException("Exception on delete in DestinationDao", ex);
        }
    }

    @Override
    public void update(Destination destination) {
        try {    
            destinationDao.update(destination);
        } catch (Exception ex) {
            throw new BasicDataAccessException("Exception on update in DestinationDao", ex);
        }
    }
    
}
