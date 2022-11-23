package com.nology.nsightapi.Classes;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import java.sql.Date;

@MappedSuperclass
public class Person {

    @NotNull
    private String name;
    @NotNull
    private String photoUrl;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    private String jobRole;

    public Person(String name, String photoUrl, Date dateOfBirth, String email, String phoneNumber, String jobRole) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.jobRole = jobRole;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
}
