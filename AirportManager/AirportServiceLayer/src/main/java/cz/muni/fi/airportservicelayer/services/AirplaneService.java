/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airportservicelayer.services;

import cz.muni.fi.airport.entity.Airplane;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jakub Stromský
 */
public interface AirplaneService {
    //DAO delegations
    /**
     * 
     * @param id
     * @return 
     */
    Airplane findById(Long id);
    
    /**
     * 
     * @return 
     */
    List<Airplane> findAllAirplanes();
    
    /**
     * 
     * @param name
     * @return 
     */
    List<Airplane> findByName(String name);
    
    /**
     * 
     * @param airplane 
     */
    void create(Airplane airplane);
    
    /**
     * 
     * @param airplane 
     */
    void remove(Airplane airplane);
    
    /**
     * 
     * @param airplane 
     */
    void update(Airplane airplane);
    
    /**
     * Find airplanes which are available in given range of dates.
     * 
     * @param fromDate initial date of range
     * @param toDate end date of range
     * @return available airplanes in given range of dates
     */
    List<Airplane> findAvailableAirplanes(Date fromDate, Date toDate);
    
    //Advanced service
    /**
     * Finds specific airplanes in sense they are available in date interval and
     * they have sufficient capacity
     * 
     * @param fromDate initial date of range
     * @param toDate end date of range
     * @param capacity required capacity of airplane
     * @param location location where airplane have to be
     * @return list of airplanes which suit for given parameters
     */
    List<Airplane> findSpecificAirplanes(Date fromDate, Date toDate, int capacity, String location);
}
