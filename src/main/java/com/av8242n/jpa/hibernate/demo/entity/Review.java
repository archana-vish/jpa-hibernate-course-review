package com.av8242n.jpa.hibernate.demo.entity;

import javax.persistence.*;


//Many reviews for one course
// Therefore, take the one side - that is the review (don't get confused here!)
// Add the mapping here!

// So this is the owning side!

@Entity
@Table(name="Review")
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String description;

    @Enumerated(EnumType.STRING) // ORDINALS ARE DEFAULT AND THEY ARE THE POSITIONS
    private ReviewRating rating;

    @ManyToOne //Read many courses can have 0..n reviews
    private Course course;

    public Review() {}

    public Review(ReviewRating rating, String description) {
        this.description = description;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
