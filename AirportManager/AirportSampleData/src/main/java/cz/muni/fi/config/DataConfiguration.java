package cz.muni.fi.config;

import cz.muni.fi.airportservicelayer.config.ServiceTestConfiguration;
import java.text.ParseException;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @author Jakub Stromský
 */
@Configuration
@Import(ServiceTestConfiguration.class)
@ComponentScan(basePackageClasses = {DataLoadingFacade.class})
public class DataConfiguration {

    private final static Logger log = LoggerFactory.getLogger(DataConfiguration.class);

    @Autowired
    private DataLoadingFacade dataLoadingFacade;

    @PostConstruct
    public void LoadData() {
        log.trace("Populate database");
        try {
            dataLoadingFacade.loadData();
        } catch (ParseException ex) {
            log.error("Population Failed on Date formatter parse", ex);
        }
    }
}
