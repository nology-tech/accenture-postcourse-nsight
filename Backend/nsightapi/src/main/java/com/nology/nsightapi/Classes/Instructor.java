package com.nology.nsightapi.Classes;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="instructors")
public class Instructor extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity = Course.class, fetch = FetchType.LAZY)
    private List<Course> courseList;

    public Instructor(String name, String photoUrl, Date dateOfBirth, String email, String phoneNumber, String jobRole, List<Course> courseList) {
        super(name, photoUrl, dateOfBirth, email, phoneNumber, jobRole);
        this.courseList = courseList;
    }

    public Instructor() {
    }

    public int getId() {
        return id;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
