package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Classes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
