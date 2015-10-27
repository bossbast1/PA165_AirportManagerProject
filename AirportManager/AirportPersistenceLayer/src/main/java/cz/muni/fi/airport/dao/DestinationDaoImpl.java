/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.dao;

import cz.muni.fi.airport.entity.Destination;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * Implementation of Data Access Object for entity Destination
 * 
 * @author Michal Zbranek
 */
@Repository
@Transactional
public class DestinationDaoImpl implements DestinationDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Destination destination) {
        em.persist(destination);
    }

    @Override
    public Destination update(Destination destination) {
        return em.merge(destination);
    }

    @Override
    public void remove(Destination destination) {
        em.remove(findById(destination.getId()));
    }

    @Override
    public Destination findById(Long id) {
        return em.find(Destination.class, id);
    }

    @Override
    public Destination findByLocation(String location) {
        return em.createQuery("SELECT d FROM Destination d WHERE d.location = :location",
				Destination.class).setParameter("location", location).getSingleResult();
    }

    @Override
    public List<Destination> findAll() {
        return em.createQuery("SELECT d FROM Destination d", Destination.class).getResultList();
    }
    
}
