package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String> {

    Optional<Instructor> findById(int id);

    Void deleteById(int id);
}
