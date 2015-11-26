/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.entity.Destination;
import java.util.List;
//import javax.inject.Inject;
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
        return destinationDao.findById(id);
    }

    @Override
    public List<Destination> findAllDestinations() {
        return destinationDao.findAll();
    }

    @Override
    public Destination findByLocation(String location) {
        return destinationDao.findByLocation(location);
    }

    @Override
    public Long create(Destination destination) {
        destinationDao.create(destination);
        return destination.getId();
    }

    @Override
    public void remove(Destination destination) {
        destinationDao.remove(destination);
    }

    @Override
    public void update(Destination destination) {
        destinationDao.update(destination);
    }
    
}
