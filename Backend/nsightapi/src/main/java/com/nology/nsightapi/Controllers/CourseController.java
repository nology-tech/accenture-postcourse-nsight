package com.nology.nsightapi.Controllers;

import com.mysql.cj.util.StringUtils;
import com.nology.nsightapi.Entities.Course;
import com.nology.nsightapi.Exceptions.BadRequestException;
import com.nology.nsightapi.Exceptions.IdAlreadyExistsException;
import com.nology.nsightapi.Exceptions.NotFoundException;
import com.nology.nsightapi.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class CourseController {

    @Autowired
    CourseRepository repository;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            }
            Course course = repository.findById(Integer.parseInt(id)).orElseThrow(NotFoundException::new);
            return ResponseEntity.status(HttpStatus.OK).body(course);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/course")
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        try {
            if (repository.findById(course.getId()).isPresent()) {
                throw new IdAlreadyExistsException();
            }
            repository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new course.");
        } catch (IdAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id already in use.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/course")
    public ResponseEntity<String> updateCourse(@RequestBody Course course) {
        try {
            if (repository.findById(course.getId()).isEmpty()) {
                throw new NotFoundException();
            }
            repository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully updated course.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            } else if (repository.findById(Integer.parseInt(id)).isEmpty()) {
                throw new NotFoundException();
            }
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted course.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        }  catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
