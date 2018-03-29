package com.rodrigo.review;

import com.rodrigo.core.BaseEntity;
import com.rodrigo.course.Course;
import com.rodrigo.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Review extends BaseEntity {

    private int rating;
    private String description;

    @ManyToOne
    private Course course;

    @ManyToOne
    private User user;

    protected Review(){
        super();
    }

    public Review(int rating, String description) {
        this();
        this.rating = rating;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
