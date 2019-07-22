package com.av8242n.jpa.hibernate.demo.repository.springdata;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository courseSpringDataRepository;


    @Test
    public void findById() {
        Optional<Course> courseById = courseSpringDataRepository.findById(10001l);
        logger.info("{}", courseById.isPresent());

    }

}
