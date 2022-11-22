package com.nology.nsightapi.Classes;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int numberEnrolled;

    @NotNull
    private int numberEmployed;

    private Instructor courseLead;

    private Date courseStart;
    private Date courseFinish;

    @NotNull
    private CourseCategory category;

    // Auto generate when uploading to s3 bucket
    private String thumbnail;

    @NotNull
    private int numberCompleted;

    private String description;

    @NotNull
    private boolean active = false;

    @NotNull
    private List<Student> studentList;

    public Course(String name, int numberEnrolled, int numberEmployed, Instructor courseLead, Date courseStart, Date courseFinish, CourseCategory category, String thumbnail, int numberCompleted, String description, boolean active, List<Student> studentList) {
        this.name = name;
        this.numberEnrolled = numberEnrolled;
        this.numberEmployed = numberEmployed;
        this.courseLead = courseLead;
        this.courseStart = courseStart;
        this.courseFinish = courseFinish;
        this.category = category;
        this.thumbnail = thumbnail;
        this.numberCompleted = numberCompleted;
        this.description = description;
        this.active = active;
        this.studentList = studentList;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberEnrolled() {
        return numberEnrolled;
    }

    public void setNumberEnrolled(int numberEnrolled) {
        this.numberEnrolled = numberEnrolled;
    }

    public int getNumberEmployed() {
        return numberEmployed;
    }

    public void setNumberEmployed(int numberEmployed) {
        this.numberEmployed = numberEmployed;
    }

    public Instructor getCourseLead() {
        return courseLead;
    }

    public void setCourseLead(Instructor courseLead) {
        this.courseLead = courseLead;
    }

    public Date getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(Date courseStart) {
        this.courseStart = courseStart;
    }

    public Date getCourseFinish() {
        return courseFinish;
    }

    public void setCourseFinish(Date courseFinish) {
        this.courseFinish = courseFinish;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getNumberCompleted() {
        return numberCompleted;
    }

    public void setNumberCompleted(int numberCompleted) {
        this.numberCompleted = numberCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
