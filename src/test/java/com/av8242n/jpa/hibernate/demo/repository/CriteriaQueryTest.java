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
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class CriteriaQueryTest {

    @Autowired
    EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void all_courses_cq_basic() {

        // select c from course c

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using entity manager and criteria query
        TypedQuery<Course> select_c_from_course_c =
                entityManager.createQuery(cq.select(courseRoot));
        List resultList = select_c_from_course_c.getResultList();
        logger.info("Value is {} ", resultList);

    }

    @Test
    public void courses_cq_basic_where_100steps() {

        // select c from course c

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate like_100_steps = cb.like(courseRoot.get("name"), "%100 steps");


        // 4. Add Predicates etc to the Criteria Query
        cq.where(like_100_steps);

        // 5. Build the TypedQuery using entity manager and criteria query
        TypedQuery<Course> select_c_from_course_c =
                entityManager.createQuery(cq.select(courseRoot));
        List resultList = select_c_from_course_c.getResultList();
        logger.info("Value is {} ", resultList);

    }

}
