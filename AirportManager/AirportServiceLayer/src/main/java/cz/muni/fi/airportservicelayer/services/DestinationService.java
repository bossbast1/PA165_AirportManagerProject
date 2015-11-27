/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.entity.Destination;
import java.util.List;

/**
 *
 * @author Michal Zbranek
 */
public interface DestinationService {
    /**
     * Finds destination by given id
     * 
     * @param id identificator of specific Destination
     * @return destination which contains given id
     */
    Destination findById(Long id);
    
    /**
     * Finds all locations in system
     * 
     * @return list of all destinations in system
     */
    List<Destination> findAllDestinations();
    
    /**
     * Find destination at given location
     * 
     * @param location
     * @return destination at given location
     */
    List<Destination> findByLocation(String location);
    
    /**
     * Creates specific Destination
     * 
     * @param destination to be created
     * @return 
     */
    void create(Destination destination);
    
    /**
     * Removes specific Destination
     * 
     * @param destination to be removed
     */
    void remove(Destination destination);
    
    /**
     * Updates specific Destination
     * 
     * @param destination to be updated
     */
    void update(Destination destination);

}
