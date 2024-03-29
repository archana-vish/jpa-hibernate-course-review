package com.av8242n.jpa.hibernate.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // Default Strategy is single_table
public abstract class Employee {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;


    public Employee() {}

    public Employee(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //this.passport = passport;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
