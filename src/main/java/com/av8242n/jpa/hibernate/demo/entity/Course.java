package com.av8242n.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value={
        @NamedQuery(name="query_gell_all_courses", query="select c from Course c"),
        @NamedQuery(name="query_gell_all_courses_like", query="select c from Course c where name like '100%'")
})
@Table(name="Course")
@Cacheable
@SQLDelete(sql="update course set is_deleted = true where id = ?")
@Where(clause="is_deleted=false")
public class Course {

    private static Logger logger = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    private boolean isDeleted;

    @PreRemove
    private void preRemove() {
        logger.info("Setting isDeleted to true");
        this.isDeleted = true;
    }

    public Course(String name) {
        this.name = name;
    }

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @OneToMany(mappedBy="course", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy="courses")
    private List<Student> students = new ArrayList<>();

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }


    public void removeReview(Review review) {
        this.reviews.remove(review);
    }


    public void addStudent(Student student) { this.students.add(student); }

    public void removeStudent(Student student) { this.students.remove(student);}


    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                ", name='" + name + '\'' +
                '}';
    }
}
