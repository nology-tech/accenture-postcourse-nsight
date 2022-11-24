package com.nology.nsightapi.Classes;

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
    @JoinColumn(name = "instructor_id", referencedColumnName = "id", nullable = false)
    private Instructor instructorId;

    @Column(name = "course_start", nullable = true)
    private Date courseStart;

    @Column(name = "course_finish", nullable = true)
    private Date courseFinish;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_category", nullable = false)
    private CourseCategory category;

    // Auto generate when uploading to s3 bucket
    @Column(name = "thumbnail", nullable = true)
    private String thumbnail;

    @Column(name = "number_completed", nullable = false)
    private int numberCompleted;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active = false;

    @OneToMany(targetEntity = Student.class, fetch = FetchType.LAZY, mappedBy = "courseId")
    private List<Student> studentList;

    public Course(String name, int numberEnrolled, int numberEmployed, Instructor courseLead, Date courseStart, Date courseFinish, CourseCategory category, String thumbnail, int numberCompleted, String description, boolean active, List<Student> studentList) {
        this.name = name;
        this.numberEnrolled = numberEnrolled;
        this.numberEmployed = numberEmployed;
        this.instructorId = courseLead;
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

    public Instructor getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Instructor instructorId) {
        this.instructorId = instructorId;
    }
}
