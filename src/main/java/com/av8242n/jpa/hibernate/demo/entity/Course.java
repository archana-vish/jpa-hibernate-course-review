package com.av8242n.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries(value={
        @NamedQuery(name="query_gell_all_courses", query="select c from Course c"),
        @NamedQuery(name="query_gell_all_courses_like", query="select c from Course c where name like '100%'")
})
@Table(name="Course")
public class Course {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
