package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "")
@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;
}
