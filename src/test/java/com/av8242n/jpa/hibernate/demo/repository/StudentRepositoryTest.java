package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.DemoApplication;
import com.av8242n.jpa.hibernate.demo.entity.Course;
import com.av8242n.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class StudentRepositoryTest {

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    @Transactional // This is to allow the transaction for the entire test otherwise due to lazy init the passport won't be retrieved
    public void retrieveStudentAndPassportDetails() {
        logger.info("Test is running...");
        Student student = repository.findById(2L);

        Student student1 = em.find(Student.class, 20001l);
        logger.info("student -> {} ", student1);
        logger.info("passport details -> {}", student1.getPassport());


        assertEquals("E400001", student.getPassport().getNumber());
    }



}
