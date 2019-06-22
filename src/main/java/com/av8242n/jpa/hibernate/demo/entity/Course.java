package com.av8242n.jpa.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="Course")
public class Course {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="name",nullable = false)
    private String name;

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
