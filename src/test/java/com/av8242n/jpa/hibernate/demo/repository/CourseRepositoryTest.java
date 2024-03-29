package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.DemoApplication;
import com.av8242n.jpa.hibernate.demo.entity.Course;
import com.av8242n.jpa.hibernate.demo.entity.Review;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void findById() {
        logger.info("Test is running...");
        Course course = repository.findById(10001L);
        assertEquals("JPA in 100 steps", course.getName());
    }

    @Test
    @Transactional // Because of this the first level cache kicks in..
    public void firstLevelCache() {
        Course course = repository.findById(10001L);
        logger.info("First course retrieved {} ", course);
        Course course1 = repository.findById(10001L);
        logger.info("First course again {} ", course1);

        assertEquals("JPA in 100 steps", course.getName());
        assertEquals("JPA in 100 steps", course1.getName());

    }

    @Test
    @DirtiesContext // Reset data
    public void deleteById() {
        logger.info("Delete by id");
        repository.deleteById(10002l);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext // Reset data
    public void saveACourse() {
        Course course = new Course("JUnit Testing");
        repository.ifFoundUpdateElseSave(course);
        assertEquals(2, course.getId());
    }

    @Test
    @DirtiesContext // Reset data
    public void updateACourse() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 100 steps", course.getName());

        // update this course
        course.setName("JPA and Hibernate in 100 Steps");
        repository.ifFoundUpdateElseSave(course);

        assertEquals(10001L, course.getId());

    }

    @Test
    @DirtiesContext
    public void playWithEntityManagerTest() {
        repository.playWithEntityManager();

    }

    @Test
    @DirtiesContext
    public void testNullable() {
        repository.testNullableColumn();
    }


    @Test
    @Transactional // This extends the transaction for all the method calls within this
    public void retrieveReviewsForCourse() {
        Course course = repository.findById(10001l);
        List<Review> reviews = course.getReviews();
        logger.info("Reviews are {} ", reviews);

    }

    @Test
    @Transactional // This extends the transaction for all the method calls within this
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001l);
        Course course = review.getCourse();
        logger.info("Course is {}", course);

    }

    @Test
    @Transactional // This is to allow the transaction for the entire test otherwise due to lazy init the passport won't be retrieved
    public void retrieveStudentAndCourse() {
        Course course = repository.findById(10001l);
        logger.info("Course {}", course);
        List<Student> students = course.getStudents();
        logger.info("Courses {}", students);

    }
}
