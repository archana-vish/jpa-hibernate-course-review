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

    @Override
    public String toString() {
        return "Passport{" +
                "number='" + number + '\'' +
                '}';
    }
}
