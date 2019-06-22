package com.av8242n.jpa.hibernate.demo.repository;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

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



}
