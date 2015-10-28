/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.tests;

import cz.muni.fi.airport.JpaTestContext;
import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.entity.Airplane;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Michal Zbranek
 */
@ContextConfiguration(classes = {JpaTestContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AirplaneTestClass extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private AirplaneDao airplaneDao;
    
    @Test
    public void testCreate() {         
        Airplane airA = new Airplane();
        airA.setCapacity(1);
        airA.setName("airA");
        airA.setType("typeA");
        
        Assert.isNull(airA.getId());
        airplaneDao.create(airA);
        Assert.notNull(airplaneDao.findById(airA.getId()));
    }
    
    @Test
    public void testUpdate() { 
       Airplane airA = new Airplane();
       airA.setCapacity(1);
       airA.setName("airA");
       airA.setType("typeA");
        
       airplaneDao.create(airA);
       Assert.notNull(airplaneDao.findById(airA.getId()));
       airA.setCapacity(2);
       airA.setName("airB");
       airA.setType("typeB");
       assert airA.getCapacity() == 2;
       assert airA.getName().equals("airB");
       assert airA.getType().equals("typeB");
       airplaneDao.update(airA);
       assert airplaneDao.findById(airA.getId()).getCapacity() == 2;
       assert airplaneDao.findById(airA.getId()).getName().equals("airB");
       assert airplaneDao.findById(airA.getId()).getType().equals("typeB");
    }
    
    @Test
    public void testRemove() {
        Airplane airA = new Airplane();
        airA.setCapacity(1);
        airA.setName("airA");
        airA.setType("typeA");
        
        airplaneDao.create(airA);
        Assert.notNull(airplaneDao.findById(airA.getId()));
        airplaneDao.delete(airA);
        Assert.isNull(airplaneDao.findById(airA.getId()));
    }
    
    @Test
    public void testNotFilledCapacity() {
        Airplane airA = new Airplane();
        airA.setName("airA");
        airA.setType("typeA");
        airplaneDao.create(airA);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledName() {
        Airplane airA = new Airplane();
        airA.setType("typeA");
        airA.setCapacity(1);
        airplaneDao.create(airA);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledType() {
        Airplane airA = new Airplane();
        airA.setCapacity(1);
        airA.setName("airA");
        airplaneDao.create(airA);
    }
    
    @Test(expectedExceptions = PersistenceException.class)
    public void testNameUniquness() {
        Airplane airA = new Airplane();
        Airplane airB = new Airplane();
        airA.setName("airA");
        airB.setName("airA");
        airA.setCapacity(1);
        airB.setCapacity(2);
        airA.setType("typeA");
        airB.setType("typeB");
        
        airplaneDao.create(airA);
        airplaneDao.create(airB);
    }
    
    @Test
    public void testFindAll() {
        Airplane airA = new Airplane();
        airA.setCapacity(1);
        airA.setName("airA");
        airA.setType("typeA");
        Airplane airB = new Airplane();
        airB.setCapacity(2);
        airB.setName("airB");
        airB.setType("typeB");
        Airplane airC = new Airplane();
        airC.setCapacity(3);
        airC.setName("airC");
        airC.setType("typeC");
        
        airplaneDao.create(airA);
        airplaneDao.create(airB);
        airplaneDao.create(airC);
        
        List<Airplane> airplanes = airplaneDao.findAllAirplanes();
        
        assert airplanes.size() == 3;
        assert airplanes.contains(airA);
        assert airplanes.contains(airB);
        assert airplanes.contains(airC);
    }
}
