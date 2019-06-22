package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager entityManager;

    // findById(long id)
    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    // deleteById(long id)
    public void deleteById(long id) {
       Course course =  entityManager.find(Course.class, id);
       entityManager.remove(course);
    }

    // save(Course course)
    public Course ifFoundUpdateElseSave(Course course) {
        if (course.getId() > 0l) {
            entityManager.merge(course); // update
        } else {
            entityManager.persist(course); // save
        }

        return course;
    }


    public void playWithEntityManager() {
        logger.info("play with entity manager");
        Course course1 = new Course("Guice in 100 steps");
        entityManager.persist(course1);
        Course course2 = new Course("React in 100 steps");
        entityManager.persist(course2);
        entityManager.flush();

        course1.setName("Guice2");
        course2.setName("REact2");

        // Refresh course1 from contents of the database again
        entityManager.refresh(course1); // REfreshed back from db and local changes are lost
        entityManager.flush();


    }


    // check nullable columns
    public void testNullableColumn() {
        logger.info("test nullable column");
        Course course1 = new Course("Guice in 100 steps");
        entityManager.persist(course1);


        course1.setName(null);

        entityManager.flush();


    }


}
