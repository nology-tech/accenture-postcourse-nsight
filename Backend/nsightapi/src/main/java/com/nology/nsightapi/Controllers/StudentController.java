package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Entities.Student;
import com.nology.nsightapi.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        repository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created new student.");
    }

    @PutMapping("/student")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        repository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated student.");
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        repository.deleteById(Integer.parseInt(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted student.");
    }
}
