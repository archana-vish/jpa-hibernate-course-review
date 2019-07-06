package com.av8242n.jpa.hibernate.demo.entity;


import javax.persistence.*;

@Entity
@Table(name="Passport")
public class Passport {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String number;

    // Making student as the owning side
    // So this field in passport is mapped By the field 'passport' in Student
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;


    public Passport() {}

    public Passport(String number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "number='" + number + '\'' +
                '}';
    }
}
