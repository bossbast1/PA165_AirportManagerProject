package cz.muni.fi.airport;

import cz.muni.fi.airport.dao.AirplaneDao;
import cz.muni.fi.airport.dao.DestinationDao;
import cz.muni.fi.airport.dao.StewardDao;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Jakub Stromský
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {DestinationDao.class, AirplaneDao.class, 
            StewardDao.class/*, FlightDao.class*/})
public class JpaTestContext {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(ds());
        return lcemfb;
        
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        return new JpaTransactionManager(entityManagerFactoryBean().getObject());

    }

    @Bean
    public DataSource ds() {

        EmbeddedDatabaseBuilder edbBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = edbBuilder.setType(EmbeddedDatabaseType.DERBY).build();
        return db;

    }

}
