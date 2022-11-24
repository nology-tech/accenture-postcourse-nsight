package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Controllers.StudentController;
import com.nology.nsightapi.Repositories.EmployerRepository;
import com.nology.nsightapi.Repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }
}
