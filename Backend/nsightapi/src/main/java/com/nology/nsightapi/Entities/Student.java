package com.nology.nsightapi.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="students")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = true)
    private Course courseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "consumer_or_consultant", nullable = false)
    private ConsumerOrConsultant consumerOrConsultant;


    @ManyToOne(targetEntity = Employer.class, fetch=FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "employer", referencedColumnName = "id")
    private Employer employer;

    @Column(name = "employed", nullable = false)
    private boolean employed;

    public Student(String name, String photoUrl, Date dateOfBirth, String email, String phoneNumber, String jobRole, Course courseId, ConsumerOrConsultant consumerOrConsultant, Employer employer, boolean employed) {
        super(name, photoUrl, dateOfBirth, email, phoneNumber, jobRole);
        this.courseId = courseId;
        this.consumerOrConsultant = consumerOrConsultant;
        this.employer = employer;
        this.employed = employed;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public ConsumerOrConsultant getConsumerOrConsultant() {
        return consumerOrConsultant;
    }

    public void setConsumerOrConsultant(ConsumerOrConsultant consumerOrConsultant) {
        this.consumerOrConsultant = consumerOrConsultant;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }
}


