package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, String> {

    Optional<Employer> findById(int id);

    void deleteById(int id);
}
