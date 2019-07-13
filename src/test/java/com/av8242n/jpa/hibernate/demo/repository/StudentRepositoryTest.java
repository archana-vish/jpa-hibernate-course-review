package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.DemoApplication;
import com.av8242n.jpa.hibernate.demo.entity.Course;
import com.av8242n.jpa.hibernate.demo.entity.Passport;
import com.av8242n.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

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


    @Test
    @Transactional
    public void someTestsToUnderstandTransactionsAndPersistenceContect() {

        // Retrieve a student
        Student student = em.find(Student.class, 20001l);
        // Persistence context if persistence context (pc) -> (student)

        // Retrieve a passport
        Passport passport = student.getPassport();
        // Transactional -> pc (student, passport)

        // Update Passport
        passport.setNumber("E122222");
        // Transactional -> pc (student, passport++)

        // Update student
        student.setName("Updated name");
        // Transactional -> pc (student++, passport)


        // Without transactional the problem is it is not atomic
        // So if the last query fails, the other updates would have occured.
        // To avoid this we need to mark it Transactional
        // With transactional all the queries get fired only at the end and changes sent out to db only at the end
        // Persistence context gets created as soon as we use Transactional
        // PC manages all the entities and gives access the db
        // EM is the interface to PC
    }


    @Test
    public void callAMethodInRepositoryAndSeePersistenceProvidedAtRepoLevel() {
        repository.operationToUnderstandPersistence();
    }



    @Test
    @Transactional // This is to allow the transaction for the entire test otherwise due to lazy init the passport won't be retrieved
    public void retrievePassportAssociatedStudent() {
        logger.info("Test is running...");
        Passport passport = em.find(Passport.class, 40001l);
        logger.info("passport -> {} ", passport);
        logger.info("student details -> {}", passport.getStudent());
        assertEquals(20001l, passport.getStudent().getId());
    }



    @Test
    @Transactional // This is to allow the transaction for the entire test otherwise due to lazy init the passport won't be retrieved
    public void retrieveStudentAndCourse() {
        Student student = repository.findById(20001l);
        logger.info("Student {}", student);
        List<Course> courses = student.getCourses();
        logger.info("Courses {}", courses);

    }

}
