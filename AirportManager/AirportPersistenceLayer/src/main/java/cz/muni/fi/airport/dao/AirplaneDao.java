package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Airplane;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jakub Stromský
 */
public interface AirplaneDao {
    
    public void create(Airplane a);
    public void delete(Airplane a);
    public List<Airplane> findAllAirplanes();
    public List<Airplane> findAvailableAirplanes(Date from, Date to);
    public Airplane findById(Long id);
    
}
