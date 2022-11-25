package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findById(int id);

    void deleteById(int id);
}
