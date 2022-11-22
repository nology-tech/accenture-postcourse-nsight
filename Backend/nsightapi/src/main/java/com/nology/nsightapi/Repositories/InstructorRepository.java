package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Classes.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, String> {
}
