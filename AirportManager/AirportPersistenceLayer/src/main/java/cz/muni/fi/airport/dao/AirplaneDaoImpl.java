package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Airplane;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jakub Stromský
 */

public class AirplaneDaoImpl implements AirplaneDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Airplane a) {
        em.persist(a);
    }

    @Override
    public void delete(Airplane a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Airplane> findAllAirplanes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Airplane> findAvailableAirplanes(Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Airplane findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
