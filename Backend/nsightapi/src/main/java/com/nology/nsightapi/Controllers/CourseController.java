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

//    @GetMapping("/courses")
//    public ResponseEntity<List<Course>> getCourses() {
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
//    }

//    @GetMapping("/course/{id}")
//    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(Integer.parseInt(id)));
//    }

//    @GetMapping("/course/instructor/{id}")
//    public ResponseEntity<List<Course>> getCoursesByInstructorId(@PathVariable String id) {
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findCoursesByInstructorId(id));
//    }

//    @PostMapping("/course")
//    public ResponseEntity<String> createCourse(@RequestBody Course course) {
//        try {
//            repository.save(course);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new course.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create new course.");
//        }
//    }
}
