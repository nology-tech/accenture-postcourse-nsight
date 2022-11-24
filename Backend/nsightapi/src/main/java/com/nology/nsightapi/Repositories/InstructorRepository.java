package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Classes.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String> {

    Instructor findById(int id);

    void deleteById(int id);
}
