package com.av8242n.jpa.hibernate.demo.repository.springdata;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
}
