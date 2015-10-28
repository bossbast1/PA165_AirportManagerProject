package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Airplane;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jakub Stromský
 */
@Repository
public class AirplaneDaoImpl implements AirplaneDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Airplane a) {
        em.persist(a);
    }

    @Override
    public void delete(Airplane a) {
        em.remove(a);
    }
    
     @Override
    public void update(Airplane a) {
        em.merge(a);
    }

    @Override
    public List<Airplane> findAllAirplanes() {
        return em.createQuery("SELECT a FROM Airplane a").getResultList();
    }

    @Override
    public List<Airplane> findAvailableAirplanes(Date fromDate, Date toDate, String origin) {
        throw new UnsupportedOperationException("Not ready to use");
        //TODO Jakub Stromský after flight completion
       /* return em.createQuery("SELECT a FROM Airplane a WHERE a NOT IN "
                + "(SELECT f.airplane FROM Flight f WHERE :from < f.departure OR :to > f.arrival) AND"
                + " SELECT MAX(f.arrival").getResultList(); //Není hotovo */
    }

    @Override
    public Airplane findById(Long id) {
        return em.find(Airplane.class, id);
    }
    
}
