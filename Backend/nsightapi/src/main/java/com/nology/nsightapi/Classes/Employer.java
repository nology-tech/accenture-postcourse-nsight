package com.nology.nsightapi.Classes;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="employers")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String employer_name;

    @OneToMany(targetEntity = Student.class, fetch = FetchType.LAZY)
    private List<Student> studentList;

    public Employer(String name, List<Student> studentList) {
        this.employer_name = name;
        this.studentList = studentList;
    }

    public Employer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return employer_name;
    }

    public void setName(String name) {
        this.employer_name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
