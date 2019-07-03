package com.av8242n.jpa.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="Review")
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String description;

    @Column
    private String rating;

    public Review() {}

    public Review(String rating, String description) {
        this.description = description;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
