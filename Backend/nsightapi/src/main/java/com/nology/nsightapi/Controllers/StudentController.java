package com.nology.nsightapi.Controllers;

import com.mysql.cj.util.StringUtils;
import com.nology.nsightapi.Entities.Student;
import com.nology.nsightapi.Exceptions.BadRequestException;
import com.nology.nsightapi.Exceptions.IdAlreadyExistsException;
import com.nology.nsightapi.Exceptions.NotFoundException;
import com.nology.nsightapi.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            }
            Student student = repository.findById(Integer.parseInt(id)).orElseThrow(NotFoundException::new);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            if (repository.findById(student.getId()).isPresent()) {
                throw new IdAlreadyExistsException();
            }
            repository.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new student.");
        } catch (IdAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id already in use.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/student")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        try {
            if (repository.findById(student.getId()).isEmpty()) {
                throw new NotFoundException();
            }
            repository.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully updated student.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            } else if (repository.findById(Integer.parseInt(id)).isEmpty()) {
                throw new NotFoundException();
            }
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted student.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        }  catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
