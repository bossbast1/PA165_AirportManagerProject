package cz.muni.fi.airport.tests;

import cz.muni.fi.airport.JpaTestContext;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.entity.Destination;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Stromský
 */
@ContextConfiguration(classes = {JpaTestContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DestinationTestClass extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private DestinationDao destinationDao;
    
    private Destination destBrno;
    private Destination destOstrava;
    private Destination destPraha;
    
    @BeforeClass
    public void setUp() {
        
        destBrno = new Destination();
        destBrno.setLocation("Brno");
        
        destOstrava = new Destination();
        destOstrava.setLocation("Ostrava");
        
        destPraha = new Destination();
        destPraha.setLocation("Praha");
        
    }
    
    @Test
    public void testCreate() { 
        Assert.isNull(destBrno.getId());
        destinationDao.create(destBrno);
        Assert.notNull(destinationDao.findById(destBrno.getId()));
    }
    
    @Test
    public void testUpdate() { 
       destinationDao.create(destPraha);
       Assert.notNull(destinationDao.findById(destPraha.getId()));
       destPraha.setLocation("Praha-Ruzyne");
       assert destPraha.getLocation().equals("Praha-Ruzyne");
       destinationDao.update(destPraha);
       assert destinationDao.findById(destPraha.getId()).getLocation().equals("Praha-Ruzyne");
    }
    
    @Test
    public void testRemove() {
        destinationDao.create(destBrno);
        Assert.notNull(destinationDao.findById(destBrno.getId()));
        destinationDao.remove(destBrno);
        Assert.isNull(destinationDao.findById(destBrno.getId()));
    }
}