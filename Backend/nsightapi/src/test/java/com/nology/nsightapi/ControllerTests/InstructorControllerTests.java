package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Classes.Instructor;
import com.nology.nsightapi.Controllers.InstructorController;
import com.nology.nsightapi.Repositories.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void givenInstructorObject_whenCreateInstructor_returnSuccessString() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());

        given(instructorRepository.save(any(Instructor.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

//        response.andDo(print());

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully created new instructor.")));
    }

    @Test
    public void givenInstructorObject_whenGetInstructors_returnListOfInstructors() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());
        when(instructorRepository.findAll()).thenReturn(List.of(instructor));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/instructors"));

//        response.andDo(print());

        response.andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(1)))
                .andExpect(jsonPath("$[0].id", is(instructor.getId())))
                .andExpect(jsonPath("$[0].name", is(instructor.getName())))
                .andExpect(jsonPath("$[0].photoUrl", is(instructor.getPhotoUrl())))
                .andExpect(jsonPath("$[0].dateOfBirth", is(dateFormatter.format(instructor.getDateOfBirth()))))
                .andExpect(jsonPath("$[0].email", is(instructor.getEmail())))
                .andExpect(jsonPath("$[0].phoneNumber", is(instructor.getPhoneNumber())))
                .andExpect(jsonPath("$[0].jobRole", is(instructor.getJobRole())))
                .andExpect(jsonPath("$[0].courses", is(instructor.getCourses())));
    }

    @Test
    public void givenInstructorObject_whenGetInstructorById_returnInstructorObject() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());
        when(instructorRepository.findById(0)).thenReturn(instructor);

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/instructor/0"));

//        response.andDo(print());

        response.andExpect(jsonPath("$.id", is(instructor.getId())))
                .andExpect(jsonPath("$.name", is(instructor.getName())))
                .andExpect(jsonPath("$.photoUrl", is(instructor.getPhotoUrl())))
                .andExpect(jsonPath("$.dateOfBirth", is(dateFormatter.format(instructor.getDateOfBirth()))))
                .andExpect(jsonPath("$.email", is(instructor.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(instructor.getPhoneNumber())))
                .andExpect(jsonPath("$.jobRole", is(instructor.getJobRole())))
                .andExpect(jsonPath("$.courses", is(instructor.getCourses())));

    }

}
