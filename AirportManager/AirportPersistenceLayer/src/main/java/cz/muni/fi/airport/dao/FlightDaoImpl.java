/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Flight;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabriela Podolnikova
 */
@Repository
@Transactional
public class FlightDaoImpl implements FlightDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Flight f) {
        em.persist(f);
    }

    @Override
    public void delete(Flight f) {
        em.remove(findById(f.getId()));
    }

    @Override
    public Flight update(Flight f) {
        return em.merge(f);
    }

    @Override
    public Flight findById(Long id) {
        return em.find(Flight.class, id);
    }

    @Override
    public List<Flight> findAll() {
        return em.createQuery("SELECT f FROM Flight f", Flight.class).getResultList();
    }

    @Override
    public List<Flight> listByDate(boolean arrival) {
        if (arrival) {
            return em.createQuery("SELECT f FROM Flight f ORDER BY f.arrival ASC", Flight.class).getResultList();
        }else {
            return em.createQuery("SELECT f FROM Flight f ORDER BY f.departure ASC", Flight.class).getResultList();
        }
    }
    
}
