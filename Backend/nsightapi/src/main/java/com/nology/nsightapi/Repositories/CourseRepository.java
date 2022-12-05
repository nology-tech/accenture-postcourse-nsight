package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Optional<Course> findById(int id);

    void deleteById(int id);
}
