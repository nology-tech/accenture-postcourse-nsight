package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Classes.Course;
import com.nology.nsightapi.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class CourseController {

    @Autowired
    CourseRepository repository;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(Integer.parseInt(id)));
    }

    @GetMapping("/course/instructor/{id}")
    public ResponseEntity<List<Course>> getCoursesByInstructorId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findCoursesByInstructorId(id));
    }

    @PostMapping("/course")
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        try {
            repository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new course.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create new course.");
        }
    }

    @PutMapping("/course")
    public ResponseEntity<String> updateCourse(@RequestBody Course newCourse) {
        try {
            repository.save(newCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated course.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update course.");
        }
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        try {
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted instructor.");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete instructor.");
        }
    }
}
