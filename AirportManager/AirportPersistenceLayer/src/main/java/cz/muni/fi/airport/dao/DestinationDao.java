/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Destination;
import java.util.List;

/**
 * Interface for Data Access Object for entity Destination
 * 
 * @author Michal Zbranek
 */
public interface DestinationDao {
    /**
     * Creates new entity of Destination
     * @param destination destination to be persisted
     */
    public void create(Destination destination);
    
    /**
     * Update the entity of Destination
     * @param destination destination to be updated
     * @return the managed instance of destination that the state was merged to
     */
    public Destination update(Destination destination);
    
    /**
     * Removes the entity of Destination
     * @param destination destination to be deleted
     */
    public void remove(Destination destination);
    
    /**
     * Finds the entity of destination by its ID
     * @param id id of destination
     * @return Destination with given ID
     */
    public Destination findById(Long id);
    /**
     * Finds the entity of destination by its location
     * @param location location of destination
     * @return Destination with given location
     */
    public Destination findByLocation(String location);
    
    /**
     * lists all persisted destinations entities
     * @return all destinations entities
     */
    public  List<Destination> findAll();
}
