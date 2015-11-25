/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.airport.tests;

import cz.muni.fi.airport.JpaTestContext;
import cz.muni.fi.airport.dao.StewardDao;
import cz.muni.fi.airport.entity.Steward;
import cz.muni.fi.airport.enums.Gender;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author Gabriela Podolnikova
 */
@ContextConfiguration(classes = {JpaTestContext.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StewardDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private StewardDao stewardDao;
    
    Steward s1;
    Steward s2;
    SimpleDateFormat formatter;
    

    @BeforeMethod
    public void setUp() {
        s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        s2 = new Steward();
        Date birth2 = null;
        Date employment2 = null;
        try {
            birth2 = formatter.parse("1985/02/08");
            employment2 = formatter.parse("2013/04/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s2.setDateOfBirth(birth2);
        s2.setEmploymentDate(employment2);
        s2.setFirstname("Peter");
        s2.setSurname("Malick");
        s2.setGender(Gender.MALE);
        s2.setPersonalIdentificator("123-23456");
        
    }

    @Test
    public void testCreate() {    
        Assert.isNull(s1.getId());
        stewardDao.create(s1);
        Assert.notNull(stewardDao.findById(s1.getId()));     
    }
    
    @Test
    public void testUpdate() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        stewardDao.create(s1);
        Assert.notNull(stewardDao.findById(s1.getId()));
        s1.setSurname("Johnson");
        assert s1.getSurname().equals("Johnson");
        stewardDao.update(s1);
        assert stewardDao.findById(s1.getId()).getSurname().equals("Johnson");
    }
    
    @Test
    public void testRemove() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        stewardDao.create(s1);
        Assert.notNull(stewardDao.findById(s1.getId()));
        stewardDao.remove(s1);
        Assert.isNull(stewardDao.findById(s1.getId()));
    }
    
    @Test
    public void testFindByIdentificator() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        stewardDao.create(s1);
        assert stewardDao.findByIdentificator("123-12345").equals(s1);
    }
    
    @Test
    public void testFindByName() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
                
        Steward s2 = new Steward();
        Date birth2 = null;
        Date employment2 = null;
        try {
            birth2 = formatter.parse("1985/02/08");
            employment2 = formatter.parse("2013/04/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s2.setDateOfBirth(birth2);
        s2.setEmploymentDate(employment2);
        s2.setFirstname("Emma");
        s2.setSurname("Stevenson");
        s2.setGender(Gender.MALE);
        s2.setPersonalIdentificator("123-23456");
        
        Steward s3 = new Steward();
        Date birth3 = null;
        Date employment3 = null;
        try {
            birth3 = formatter.parse("1984/02/08");
            employment3 = formatter.parse("2014/04/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s3.setDateOfBirth(birth2);
        s3.setEmploymentDate(employment2);
        s3.setFirstname("Stevenson");
        s3.setSurname("Stevenson");
        s3.setGender(Gender.MALE);
        s3.setPersonalIdentificator("123-23457");
        
        Steward s4 = new Steward();
        Date birth4 = null;
        Date employment4 = null;
        try {
            birth4 = formatter.parse("1984/02/08");
            employment4 = formatter.parse("2010/04/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s4.setDateOfBirth(birth2);
        s4.setEmploymentDate(employment2);
        s4.setFirstname("Emma");
        s4.setSurname("Emma");
        s4.setGender(Gender.FEMALE);
        s4.setPersonalIdentificator("123-23458");
        
        stewardDao.create(s1);
        stewardDao.create(s2);
        stewardDao.create(s3);
        stewardDao.create(s4);
        
        List<Steward> stewards = stewardDao.findByName("Emma", "Stevenson");
        
        assert stewards.size() == 2;
        assert stewards.contains(s1);
        assert stewards.contains(s2);
    }
    
    @Test
    public void testFindAll() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
                
        Steward s2 = new Steward();
        Date birth2 = null;
        Date employment2 = null;
        try {
            birth2 = formatter.parse("1985/02/04");
            employment2 = formatter.parse("2013/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s2.setDateOfBirth(birth2);
        s2.setEmploymentDate(employment2);
        s2.setFirstname("Joey");
        s2.setSurname("Johnson");
        s2.setGender(Gender.MALE);
        s2.setPersonalIdentificator("123-23456");
        
        stewardDao.create(s1);
        stewardDao.create(s2);
        
        List<Steward> stewards = stewardDao.findAll();
        
        assert stewards.size() == 2;
        assert stewards.contains(s1);
        assert stewards.contains(s2);
        
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledPersonalIdentificator() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        stewardDao.create(s1);

    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testIncorrectlyFilledPersonalIdentificator() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        
        s1.setPersonalIdentificator("Invalid");
        
        stewardDao.create(s1);

    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledFirstname() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        stewardDao.create(s1);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledSurname() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        stewardDao.create(s1);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledGender() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setPersonalIdentificator("123-12345");
        
        stewardDao.create(s1);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledDateOfBirt() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setEmploymentDate(employment);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        stewardDao.create(s1);
    }
    
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNotFilledEmploymentDate() {
        Steward s1 = new Steward();
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date birth = null;
        Date employment = null;
        try {
            birth = formatter.parse("1988/02/02");
            employment = formatter.parse("2014/03/01");
        } catch (ParseException ex) {
            Logger.getLogger(StewardDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        s1.setDateOfBirth(birth);
        s1.setFirstname("Emma");
        s1.setSurname("Stevenson");
        s1.setGender(Gender.FEMALE);
        s1.setPersonalIdentificator("123-12345");
        
        stewardDao.create(s1);
    }
    
    @Test(expectedExceptions = PersistenceException.class)
    public void testPersonalIdentificatorUniquness() {
        s2.setPersonalIdentificator("123-12345");
        stewardDao.create(s1);
        stewardDao.create(s2);
    }
}
