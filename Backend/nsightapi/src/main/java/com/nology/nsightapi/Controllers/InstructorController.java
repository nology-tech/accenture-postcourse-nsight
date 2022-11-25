package com.nology.nsightapi.Controllers;

import com.mysql.cj.util.StringUtils;
import com.nology.nsightapi.Entities.Instructor;
import com.nology.nsightapi.Exceptions.BadRequestException;
import com.nology.nsightapi.Exceptions.IdAlreadyExistsException;
import com.nology.nsightapi.Exceptions.NotFoundException;
import com.nology.nsightapi.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class InstructorController {

    @Autowired
    private InstructorRepository repository;

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> getInstructors() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            }
            Instructor instructor = repository.findById(Integer.parseInt(id)).orElseThrow(NotFoundException::new);
            return ResponseEntity.status(HttpStatus.OK).body(instructor);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/instructor")
    public ResponseEntity<String> createInstructor(@RequestBody Instructor instructor) {
        try {
            if (repository.findById(instructor.getId()).isPresent()) {
                throw new IdAlreadyExistsException();
            }
            repository.save(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new instructor.");
        } catch (IdAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id already in use.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/instructor")
    public ResponseEntity<String> updateInstructor(@RequestBody Instructor instructor) {
        try {
            if (repository.findById(instructor.getId()).isEmpty()) {
                throw new NotFoundException();
            }
            repository.save(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully updated instructor.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            } else if (repository.findById(Integer.parseInt(id)).isEmpty()) {
                throw new NotFoundException();
            }
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted instructor.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        }  catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
