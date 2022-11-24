package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Classes.Instructor;
import com.nology.nsightapi.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class InstructorController {

    @Autowired
    private InstructorRepository repository;

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> getInstructors() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/instructor")
    public ResponseEntity<String> createInstructor(@RequestBody Instructor instructor) {
        try {
            repository.save(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new instructor.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create new instructor.");
        }
    }

    @PutMapping("/instructor")
    public ResponseEntity<String> updateInstructor(@RequestBody Instructor instructor) {
        try {
            repository.save(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new instructor.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create new instructor.");
        }
    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable String id) {
        try {
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted instructor.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete instructor.");
        }
    }
}
