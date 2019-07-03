package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.entity.Passport;
import com.av8242n.jpa.hibernate.demo.entity.Student;
import com.av8242n.jpa.hibernate.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    // findById(long id)
    public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }

    // deleteById(long id)
    public void deleteById(long id) {
       Student student =  entityManager.find(Student.class, id);
       entityManager.remove(student);
    }

    // save(Student student)
    public Student ifFoundUpdateElseSave(Student student) {
        if (student.getId() > 0l) {
            entityManager.merge(student); // update
        } else {
            entityManager.persist(student); // save
        }

        return student;
    }

    // save(Student student)
    public void saveStudentWithPassport() {
        Passport passort = new Passport("E400001");
        entityManager.persist(passort);

        // Student is the owning side so first the passport must exist in the db
        Student student = new Student("Sven");
        student.setPassport(passort);
        entityManager.persist(student);
    }


    public void playWithEntityManager() {
        logger.info("play with entity manager");
        Student student1 = new Student("Guice in 100 steps");
        entityManager.persist(student1);
        Student student2 = new Student("React in 100 steps");
        entityManager.persist(student2);
        entityManager.flush();

        student1.setName("Guice2");
        student2.setName("REact2");

        // Refresh student1 from contents of the database again
        entityManager.refresh(student1); // REfreshed back from db and local changes are lost
        entityManager.flush();


    }


    // check nullable columns
    public void testNullableColumn() {
        logger.info("test nullable column");
        Student student1 = new Student("Guice in 100 steps");
        entityManager.persist(student1);


        student1.setName(null);

        entityManager.flush();


    }

    // check for createdTime and updatedTime
    public void testTimestamps() {
        Student student1 = new Student("Groovy");
        entityManager.persist(student1);

        Student student2 = findById(10002l);
        student2.setName("This shoudl change updated time");
        entityManager.persist(student2);

        entityManager.flush();

    }
}
