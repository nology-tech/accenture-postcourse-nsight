package com.nology.nsightapi.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="courses")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name", nullable = false)
    private String name;

    @Column(name = "number_enrolled", nullable = false)
    private int numberEnrolled;

    @Column(name = "number_employed", nullable = false)
    private int numberEmployed;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "instructor", referencedColumnName = "id", nullable = false)
    private Instructor instructor;

    @Column(name = "course_start")
    private Date courseStart;

    @Column(name = "course_finish")
    private Date courseFinish;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_category", nullable = false)
    private CourseCategory courseCategory;

    // Auto generate when uploading to s3 bucket
    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "number_completed", nullable = false)
    private int numberCompleted;

    @Column(name = "description")
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @OneToMany(targetEntity = Student.class, fetch = FetchType.LAZY, mappedBy = "courseId")
    private List<Student> students;

    public Course(String name, int numberEnrolled, int numberEmployed, Instructor instructor, Date courseStart, Date courseFinish, CourseCategory courseCategory, String thumbnail, int numberCompleted, String description, boolean active, List<Student> students) {
        this.name = name;
        this.numberEnrolled = numberEnrolled;
        this.numberEmployed = numberEmployed;
        this.instructor = instructor;
        this.courseStart = courseStart;
        this.courseFinish = courseFinish;
        this.courseCategory = courseCategory;
        this.thumbnail = thumbnail;
        this.numberCompleted = numberCompleted;
        this.description = description;
        this.active = active;
        this.students = students;
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
