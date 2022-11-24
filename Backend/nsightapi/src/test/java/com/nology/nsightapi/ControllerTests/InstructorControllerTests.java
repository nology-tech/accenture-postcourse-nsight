package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Controllers.InstructorController;
import com.nology.nsightapi.Repositories.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InstructorController.class)
public class InstructorControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InstructorRepository instructorRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }
}
