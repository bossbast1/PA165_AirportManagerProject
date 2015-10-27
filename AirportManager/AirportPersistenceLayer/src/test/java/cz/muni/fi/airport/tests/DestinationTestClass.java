package cz.muni.fi.airport.tests;

import cz.muni.fi.airport.JpaTestContext;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.entity.Destination;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Stromský
 */

@ContextConfiguration(classes = JpaTestContext.class)
public class DestinationTestClass extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private DestinationDao destination;
    
    @Test
    public void testCreate() {
        
        Destination d = new Destination();
        d.setLocation("Brno");
        destination.create(d);
        List<Destination> destinationList = destination.findAll();
        assert destinationList.size() == 1;
        
    }
}
