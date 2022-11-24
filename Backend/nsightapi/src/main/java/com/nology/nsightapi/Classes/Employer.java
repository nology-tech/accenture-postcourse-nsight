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
    private String name;

    @OneToMany(targetEntity = Student.class, fetch = FetchType.LAZY, mappedBy = "employer")
    private List<Student> students;

    public Employer(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Employer() {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
