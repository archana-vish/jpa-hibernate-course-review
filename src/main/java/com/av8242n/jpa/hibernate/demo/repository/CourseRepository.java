package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import com.av8242n.jpa.hibernate.demo.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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

    // check for createdTime and updatedTime
    public void testTimestamps() {
        Course course1 = new Course("Groovy");
        entityManager.persist(course1);

        Course course2 = findById(10002l);
        course2.setName("This shoudl change updated time");
        entityManager.persist(course2);

        entityManager.flush();

    }

    public void addHardcodedReviewsForCourse() {
        Course course = findById(10003l);
        logger.info("Review for this course {} ", course.getReviews());
        // Add another review now
        Review review1 = new Review("5", "Great hands on stuff");
        Review review2 = new Review("5", "Super");
        course.addReview(review1);
        course.addReview(review2);

        // Review is the owning side. So set the course id
        review1.setCourse(course);
        review2.setCourse(course);

        // save it to the database
        entityManager.persist(review1);
        entityManager.persist(review2);

    }


    public void addReviewsForCourseGeneric(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        for (Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            entityManager.persist(review);
        }
    }




}
