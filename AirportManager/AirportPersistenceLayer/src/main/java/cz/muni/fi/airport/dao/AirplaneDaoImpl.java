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
    public Airplane findById(Long id) {
        return em.find(Airplane.class, id);
    }
    
}
