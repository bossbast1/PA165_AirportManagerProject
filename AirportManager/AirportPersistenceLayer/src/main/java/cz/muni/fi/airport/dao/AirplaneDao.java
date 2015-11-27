package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Airplane;
import cz.muni.fi.airport.entity.Flight;
import java.util.Date;
import java.util.List;

/**
 * Interface for Data Access Object for entity Airplane
 *
 * @author Jakub Stromský
 */
public interface AirplaneDao {
    
    /**
     * Creates new entity of Airplane
     * 
     * @param a airplane to be persisted
     */
    public void create(Airplane a);
    
    /**
     * Removes entity of Airplane
     * 
     * @param a airplane to be removed
     */
    public void delete(Airplane a);
    
    /**
     * Updates entity of Airplane
     * 
     * @param a airplane to be updated
     */
    public void update(Airplane a);
    
    /**
     * Finds all entities of Airplane
     * 
     * @return list of Airplane entities
     */
    public List<Airplane> findAllAirplanes();
    
    /**
     * Finds entity of Airplane by given identificator
     * 
     * @param id identificator of airplane
     * @return airplane with given ID
     */
    public Airplane findById(Long id);
    
    /**
     * Finds entity of Airplane with given name
     * 
     * @param name name of airplane
     * @return airplane with given name
     */
    public List<Airplane> findByName(String name);
    
    /**
     * Finds entities of Airplanes which are available in given range of dates. <br>
     * That means airplanes which don't have any scheduled flight in range of given dates.
     * 
     * @param fromDate initial date of interval
     * @param toDate end date of interval
     * @return all airplanes which are available in given date interval
     */
    public List<Airplane> findAvailableAirplanes(Date fromDate, Date toDate); 
    
    /**
     * Finds last flights of given airplane
     * 
     * @param a airplane for which last flight is found
     * @return list of last flights of given airplane
     */
    public List<Flight> findAirplaneFlights(Airplane a);
    
}
