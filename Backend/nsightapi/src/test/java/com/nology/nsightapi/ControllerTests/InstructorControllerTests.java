package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Entities.Instructor;
import com.nology.nsightapi.Controllers.InstructorController;
import com.nology.nsightapi.Repositories.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    public void givenInstructorObject_whenGetInstructors_returnListOfInstructors() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());
        given(instructorRepository.findAll()).willReturn(List.of(instructor));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/instructors"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
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
    public void givenEmptyRepository_whenGetInstructors_returnEmptyList() throws Exception {

        ResultActions response = mockMvc.perform(get("/instructors"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(0)));
    }

    @Test
    public void givenInstructorObject_whenGetInstructorById_returnInstructorObject() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());
        given(instructorRepository.findById(0)).willReturn(Optional.of(instructor));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/instructor/0"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(instructor.getId())))
                .andExpect(jsonPath("$.name", is(instructor.getName())))
                .andExpect(jsonPath("$.photoUrl", is(instructor.getPhotoUrl())))
                .andExpect(jsonPath("$.dateOfBirth", is(dateFormatter.format(instructor.getDateOfBirth()))))
                .andExpect(jsonPath("$.email", is(instructor.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(instructor.getPhoneNumber())))
                .andExpect(jsonPath("$.jobRole", is(instructor.getJobRole())))
                .andExpect(jsonPath("$.courses", is(instructor.getCourses())));

    }

    @Test
    public void givenEmptyRepository_whenGetInstructorByInvalidId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(get("/instructor/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Instructor not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmptyRepository_whenGetInstructorByNonNumericId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(get("/instructor/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenInstructorObject_whenCreateInstructor_returnSuccessString() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());

        given(instructorRepository.save(any(Instructor.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully created new instructor.")));
    }

    @Test
    public void givenInstructorObject_whenCreateInstructorWithExistingId_throwIdAlreadyExistsException() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());

        given(instructorRepository.findById(any(Integer.class))).willReturn(Optional.of(instructor));

        ResultActions response = mockMvc.perform(post("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("406 NOT_ACCEPTABLE \"Id already in use.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenInstructorObject_whenCreateInstructorWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Instructor instructor = new Instructor(null, null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());

        given(instructorRepository.save(any(Instructor.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(post("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenInstructorObject_whenPutInstructor_returnSuccessString() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());
        given(instructorRepository.findById(0)).willReturn(Optional.of(instructor));
        given(instructorRepository.save(any(Instructor.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

//        response.andDo(print());

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully updated instructor.")));
    }

    @Test
    public void givenInstructorObject_whenPutInstructorWithNoExistingInstructorId_returnNotFoundException() throws Exception {

        Instructor instructor = new Instructor("Test Name", null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());

        given(instructorRepository.save(any(Instructor.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Instructor not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenInstructorObject_whenPutInstructorWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Instructor instructor = new Instructor(null, null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());

        given(instructorRepository.findById(any(Integer.class))).willReturn(Optional.of(instructor));
        given(instructorRepository.save(any(Instructor.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(put("/instructor").content(objectMapper.writeValueAsString(instructor)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenInstructorObject_whenDeleteInstructorById_returnSuccessString() throws Exception {
        Instructor instructor = new Instructor(null, null, new Date(20221124L), "testemail@gmail.com", "12345678910", "Test role", new ArrayList<>());
        given(instructorRepository.findById(0)).willReturn(Optional.of(instructor));

        ResultActions response = mockMvc.perform(delete("/instructor/0"));

        response.andExpect(status().isNoContent())
                .andExpect(jsonPath("$", is("Deleted instructor.")));
    }

    @Test
    public void givenInstructorObject_whenDeleteInstructorWithNoExistingInstructorId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/instructor/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Instructor not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenInstructorObject_whenDeleteInstructorWithInvalidInstructorId_throwBadRequestException() throws Exception {
        given(instructorRepository.deleteById(any(Integer.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(delete("/instructor/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}

