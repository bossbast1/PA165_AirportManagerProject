/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.entity.Flight;
import cz.muni.fi.airport.entity.Steward;
import java.util.List;

/**
 *
 * @author Sebastian Kupka
 */
public interface StewardService {
    /**
     * Gets Steward DTO with given id.
     * @param id identificator
     * @return 
     */
    public Steward findById(Long id);
    
    /**
     * Gets Steward DTO with personal identificator.
     * @param personalIdentificator personal identificator
     * @return 
     */
    public Steward findByPersonalIdentificator(String personalIdentificator);
    
    /**
     * Lists all Stewards.
     * @return 
     */
    public List<Steward> getAllStewards();
    
    /**
     * Lists all Stewards with given name.
     * @param name first name
     * @param surname surname
     * @return 
     */
    public List<Steward> findByName(String name, String surname);
    
    /**
     * Creates new Steward
     * @param steward 
     */
    public Long createSteward(Steward steward);
    
    /**
     * Removes Steward
     * @param id identificator
     */
    public void removeSteward(Long id);
    
    /**
     * Updates Steward
     * @param update Update with new name
     */
    public void updateSteward(Steward update);
    
    /**
     * Lists all Stewards at given location.
     * @param locationId identificator of the destination.
     * @return 
     */
    public List<Steward> getAvailableStewardsAtLocation(Long locationId);
    
    /**
     * Lists all flights for given Steward
     * @param id Steward's identificator
     * @return List of all flights for given Steward
     */
    public List<Flight> getStewardFlights(Long id);   
}
