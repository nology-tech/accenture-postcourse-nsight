package com.nology.nsightapi.Controllers;

import com.nology.nsightapi.Repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "")
@RestController
public class EmployerController {

    @Autowired
    private EmployerRepository repository;

}
