package com.nology.nsightapi.Classes;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employer_name", nullable = false)
    private String employer_name;

    @OneToMany(targetEntity = Student.class, fetch = FetchType.LAZY, mappedBy = "employerId")
    private List<Student> studentIds;

    public Employer(String name, List<Student> studentList) {
        this.employer_name = name;
        this.studentIds = studentList;
    }

    public Employer() {
    }

    public int getId() {
        return id;
    }

    public String getEmployer_name() {
        return employer_name;
    }

    public void setEmployer_name(String employer_name) {
        this.employer_name = employer_name;
    }

    public List<Student> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Student> studentIds) {
        this.studentIds = studentIds;
    }
}
