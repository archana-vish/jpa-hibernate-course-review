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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class JPQLTest {

    @Autowired
    EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void jpql_basic() {
        Query select_c_from_course_c = entityManager.createQuery("select c from course c");
        List resultList = select_c_from_course_c.getResultList();
        logger.info("Value is {} ", resultList);

    }

    // Use typesafe
    @Test
    public void jpql_typed() {
        TypedQuery<Course> select_c_from_course_c = entityManager.createQuery("select c from Course c", Course.class);
        List<Course> resultList = select_c_from_course_c.getResultList();
        logger.info("Value is {} ", resultList);

    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> select_c_from_course_c =
                entityManager.createQuery("select c from Course c where name like '%100%'", Course.class);
        List<Course> resultList = select_c_from_course_c.getResultList();
        logger.info("Value is {} ", resultList);

    }

    @Test
    public void namedQuery_JPQL() {
        TypedQuery<Course> query_gell_all_courses = entityManager.createNamedQuery("query_gell_all_courses", Course.class);
        List<Course> resultList = query_gell_all_courses.getResultList();
        logger.info("Results are {} ", resultList);

        TypedQuery<Course> query_get_all_like_100 = entityManager.createNamedQuery("query_gell_all_courses_like", Course.class);
        List<Course> resultSet2 = query_get_all_like_100.getResultList();
        logger.info("like 100 {}", resultSet2);


    }
}
