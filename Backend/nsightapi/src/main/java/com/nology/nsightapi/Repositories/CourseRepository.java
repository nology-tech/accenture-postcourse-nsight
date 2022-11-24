package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Classes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Course findById(int id);

    void deleteById(int id);

    @Query(value = "SELECT * FROM courses WHERE instructor_id = ?1", nativeQuery = true)
    List<Course> findCoursesByInstructorId(@Param("id") String id);
}
