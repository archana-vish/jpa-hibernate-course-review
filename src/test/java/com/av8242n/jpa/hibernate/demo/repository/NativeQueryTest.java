package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.DemoApplication;
import com.av8242n.jpa.hibernate.demo.entity.Course;
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
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class NativeQueryTest {

    @Autowired
    EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void testNativeQuery() {
        Query query = entityManager.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 10001l);
        List<Course> resultList = query.getResultList();
        logger.info("select * from course -> {} ", resultList);

    }

    @Test
    @Transactional
    public void updateQuery() {
        Query query = entityManager.createNativeQuery("update course set updated_date_time = sysdate()", Course.class);
        int rows = query.executeUpdate();
        logger.info("updated rows {}", rows);
    }

}
