package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Classes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    Course findById(int id);

//    @Query(value = "SELECT * FROM courses WHERE courseLead = ?1")
//    List<Course> findCoursesByInstructorId(@Param("id") String id);
}
