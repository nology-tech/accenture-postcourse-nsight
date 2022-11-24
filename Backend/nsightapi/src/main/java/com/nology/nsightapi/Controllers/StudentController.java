package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Classes.Student;
import com.nology.nsightapi.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
}
