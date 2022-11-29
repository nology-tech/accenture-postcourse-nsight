package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findById(int id);

    Void deleteById(int id);
}
