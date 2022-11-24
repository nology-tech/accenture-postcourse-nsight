package com.nology.nsightapi.Classes;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructors")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instructor extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="instructorId")
    private List<Course> courseIds = new ArrayList<>();

    public Instructor(String name, String photoUrl, Date dateOfBirth, String email, String phoneNumber, String jobRole, List<Course> courseList) {
        super(name, photoUrl, dateOfBirth, email, phoneNumber, jobRole);
        this.courseIds = courseList;
    }

    public Instructor() {
    }

    public int getId() {
        return id;
    }

    public List<Course> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Course> courseIds) {
        this.courseIds = courseIds;
    }
}
