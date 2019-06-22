package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.DemoApplication;
import com.av8242n.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void findById() {
        logger.info("Test is running...");
        Course course = repository.findById(10001L);
        assertEquals("JPA in 100 steps", course.getName());
    }

    @Test
    @DirtiesContext // Reset data
    public void deleteById() {
        logger.info("Delete by id");
        repository.deleteById(10002l);
        assertNull(repository.findById(10002L));
    }


}
