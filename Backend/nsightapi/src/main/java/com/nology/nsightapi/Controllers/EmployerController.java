package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Entities.Employer;
import com.nology.nsightapi.Repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class EmployerController {

    @Autowired
    private EmployerRepository repository;


    @GetMapping("/employers")
    public ResponseEntity<List<Employer>> getEmployers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    @GetMapping("/employer/{id}")
    public ResponseEntity<Employer> getEmployer(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/employer")
    public ResponseEntity<String> createEmployer(@RequestBody Employer employer) {
        repository.save(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new employer.");
    }

    @PutMapping("/employer")
    public ResponseEntity<String> updateEmployer(@RequestBody Employer newEmployer) {
        try {
            repository.save(newEmployer);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated employer.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update employer.");
        }
    }

    @DeleteMapping("/employer/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable String id) {
        try {
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted employer.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete employer.");
        }
    }
}
