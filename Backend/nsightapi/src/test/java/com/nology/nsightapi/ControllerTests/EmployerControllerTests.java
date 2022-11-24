package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Controllers.EmployerController;
import com.nology.nsightapi.Repositories.EmployerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployerController.class)
public class EmployerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployerRepository employerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }
}
