package cz.muni.fi.airport.tests;

import cz.muni.fi.airport.JpaTestContext;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.entity.Destination;
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
 * @author Jakub Stromský
 */
@ContextConfiguration(classes = {JpaTestContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DestinationDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private DestinationDao destinationDao;
            
    @Test
    public void testCreate() {         
        Destination destBrno = new Destination();
        destBrno.setLocation("Brno");
        
        Assert.isNull(destBrno.getId());
        destinationDao.create(destBrno);
        Assert.notNull(destinationDao.findById(destBrno.getId()));
    }
    
    @Test
    public void testUpdate() { 
       Destination destPraha = new Destination();
       destPraha.setLocation("Praha");
        
       destinationDao.create(destPraha);
       Assert.notNull(destinationDao.findById(destPraha.getId()));
       destPraha.setLocation("Praha-Ruzyne");
       assert destPraha.getLocation().equals("Praha-Ruzyne");
       destinationDao.update(destPraha);
       assert destinationDao.findById(destPraha.getId()).getLocation().equals("Praha-Ruzyne");
    }
    
    @Test
    public void testRemove() {
        Destination destBrno = new Destination();
        destBrno.setLocation("Brno");
        
        destinationDao.create(destBrno);
        Assert.notNull(destinationDao.findById(destBrno.getId()));
        destinationDao.remove(destBrno);
        Assert.isNull(destinationDao.findById(destBrno.getId()));
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledLocation() {
        Destination destOstrava = new Destination();
        destinationDao.create(destOstrava);
    }
    
    @Test(expectedExceptions = PersistenceException.class)
    public void testLocationUniquness() {
        Destination destPraha = new Destination();
        Destination destPraha2 = new Destination();
        destPraha.setLocation("Praha");
        destPraha2.setLocation("Praha");
        
        destinationDao.create(destPraha);
        destinationDao.create(destPraha2);
    }
    
    @Test
    public void testFindByLocation() {
        Destination destPraha = new Destination();
        destPraha.setLocation("Praha");
        
        destinationDao.create(destPraha);
        assert destinationDao.findByLocation("Praha").equals(destPraha);
    }
    
    @Test
    public void testFindAll() {
        Destination destPraha = new Destination();
        Destination destBrno = new Destination();
        Destination destOstrava = new Destination();
        destBrno.setLocation("Brno");
        destOstrava.setLocation("Ostrava");
        destPraha.setLocation("Praha");
        
        destinationDao.create(destBrno);
        destinationDao.create(destOstrava);
        destinationDao.create(destPraha);
        
        List<Destination> destinations = destinationDao.findAll();
        
        assert destinations.size() == 3;
        assert destinations.contains(destBrno);
        assert destinations.contains(destOstrava);
        assert destinations.contains(destPraha);
    }
    
}