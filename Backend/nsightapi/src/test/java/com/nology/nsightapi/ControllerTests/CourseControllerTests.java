package com.nology.nsightapi.ControllerTests;

import com.nology.nsightapi.Controllers.CourseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CourseController.class)
public class CourseControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseRepository courseRepository;

    @Autowired
    ObjectMapper mapper;

    @Test
    void contextLoads() {
    }
}
