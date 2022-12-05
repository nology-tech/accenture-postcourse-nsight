package com.nology.nsightapi.Controllers;

import com.mysql.cj.util.StringUtils;
import com.nology.nsightapi.Entities.Employer;
import com.nology.nsightapi.Exceptions.BadRequestException;
import com.nology.nsightapi.Exceptions.IdAlreadyExistsException;
import com.nology.nsightapi.Exceptions.NotFoundException;
import com.nology.nsightapi.Repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class EmployerController {

    @Autowired
    private EmployerRepository repository;


    @GetMapping("/employers")
    public ResponseEntity<List<Employer>> getEmployers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/employer/{id}")
    public ResponseEntity<Employer> getEmployer(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            }
            Employer employer = repository.findById(Integer.parseInt(id)).orElseThrow(NotFoundException::new);
            return ResponseEntity.status(HttpStatus.OK).body(employer);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/employer")
    public ResponseEntity<String> createEmployer(@RequestBody Employer employer) {
        try {
            if (repository.findById(employer.getId()).isPresent()) {
                throw new IdAlreadyExistsException();
            }
            repository.save(employer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created new employer.");
        } catch (IdAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id already in use.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/employer")
    public ResponseEntity<String> updateEmployer(@RequestBody Employer employer) {
        try {
            if (repository.findById(employer.getId()).isEmpty()) {
                throw new NotFoundException();
            }
            repository.save(employer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully updated employer.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer not found.");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing non-null values.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/employer/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable String id) {
        try {
            if (!StringUtils.isStrictlyNumeric(id)) {
                throw new BadRequestException();
            } else if (repository.findById(Integer.parseInt(id)).isEmpty()) {
                throw new NotFoundException();
            }
            repository.deleteById(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted employer.");
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer not found.");
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non-numeric id entered.");
        }  catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
