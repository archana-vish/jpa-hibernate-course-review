package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CourseRepository {

    @PersistenceContext
    EntityManager entityManager;

    // findById(long id)
    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    // save(Course course)
    // deleteById(long id)


}
