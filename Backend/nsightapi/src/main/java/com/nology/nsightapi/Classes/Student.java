package com.nology.nsightapi.Classes;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="students")public class Student extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    Add as foreign key
    private int courseId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ConsumerOrConsultant consumerOrConsultant;


    @ManyToOne(targetEntity = Employer.class, fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @NotNull
    private boolean employed;

    public Student(String name, String photoUrl, Date dateOfBirth, String email, String phoneNumber, String jobRole, int courseId, ConsumerOrConsultant consumerOrConsultant, Employer employer, boolean employed) {
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
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
