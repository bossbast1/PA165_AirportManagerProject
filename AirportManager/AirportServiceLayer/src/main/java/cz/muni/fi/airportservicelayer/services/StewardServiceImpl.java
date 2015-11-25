/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.dao.StewardDao;
import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airportservicelayer.exceptions.BasicDataAccessException;
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
    public List<Steward> getAvailableStewardsAtLocation(Long locationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Flight> getStewardFlights(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
}
