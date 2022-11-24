package com.nology.nsightapi.Repositories;

import com.nology.nsightapi.Classes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, String> {

    Employer findById(int id);

    void deleteById(int id);
}
