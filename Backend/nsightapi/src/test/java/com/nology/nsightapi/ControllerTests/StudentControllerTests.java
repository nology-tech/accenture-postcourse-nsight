package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Controllers.StudentController;
import com.nology.nsightapi.Entities.ConsumerOrConsultant;
import com.nology.nsightapi.Entities.Course;
import com.nology.nsightapi.Entities.Student;
import com.nology.nsightapi.Repositories.StudentRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void givenStudentObject_whenGetStudents_returnListOfStudents() throws Exception {
        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", new Course(), ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.findAll()).willReturn(List.of(student));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/students"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(1)))
                .andExpect(jsonPath("$[0].id", is(student.getId())))
                .andExpect(jsonPath("$[0].name", is(student.getName())))
                .andExpect(jsonPath("$[0].photoUrl", is(student.getPhotoUrl())))
                .andExpect(jsonPath("$[0].dateOfBirth", is(dateFormatter.format(student.getDateOfBirth()))))
                .andExpect(jsonPath("$[0].email", is(student.getEmail())))
                .andExpect(jsonPath("$[0].phoneNumber", is(student.getPhoneNumber())))
                .andExpect(jsonPath("$[0].jobRole", is(student.getJobRole())))
//                .andExpect(jsonPath("$[0].courseId", isA(Course.class)))
                .andExpect(jsonPath("$[0].consumerOrConsultant", is(String.valueOf(student.getConsumerOrConsultant()))))
                .andExpect(jsonPath("$[0].employer", is(student.getEmployer())))
                .andExpect(jsonPath("$[0].employed", is(student.isEmployed())));
    }

    @Test
    public void givenEmptyRepository_whenGetStudents_returnEmptyList() throws Exception {

        ResultActions response = mockMvc.perform(get("/students"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(0)));
    }

    @Test
    public void givenStudentObject_whenGetStudentById_returnStudentObject() throws Exception {
        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", new Course(), ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/student/0"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(student.getId())))
                .andExpect(jsonPath("$.name", is(student.getName())))
                .andExpect(jsonPath("$.photoUrl", is(student.getPhotoUrl())))
                .andExpect(jsonPath("$.dateOfBirth", is(dateFormatter.format(student.getDateOfBirth()))))
                .andExpect(jsonPath("$.email", is(student.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(student.getPhoneNumber())))
                .andExpect(jsonPath("$.jobRole", is(student.getJobRole())))
//                .andExpect(jsonPath("$[0].courseId", isA(Course.class)))
                .andExpect(jsonPath("$.consumerOrConsultant", is(String.valueOf(student.getConsumerOrConsultant()))))
                .andExpect(jsonPath("$.employer", is(student.getEmployer())))
                .andExpect(jsonPath("$.employed", is(student.isEmployed())));
    }

    @Test
    public void givenEmptyRepository_whenGetStudentByInvalidId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(get("/student/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Student not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmptyRepository_whenGetStudentByNonNumericId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(get("/student/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenStudentObject_whenCreateStudent_returnSuccessString() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.save(any(Student.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/student").content(objectMapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully created new student.")));
    }

    @Test
    public void givenStudentObject_whenCreateStudentWithExistingId_throwIdAlreadyExistsException() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));

        ResultActions response = mockMvc.perform(post("/student").content(objectMapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("406 NOT_ACCEPTABLE \"Id already in use.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenStudentObject_whenCreateStudentWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.save(any(Student.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(post("/student").content(objectMapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenStudentObject_whenPutStudent_returnSuccessString() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));
        given(studentRepository.save(any(Student.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/student").content(objectMapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully updated student.")));
    }

    @Test
    public void givenStudentObject_whenPutStudentWithNoExistingStudentId_returnNotFoundException() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.save(any(Student.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/student").content(objectMapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Student not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenStudentObject_whenPutStudentWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));
        given(studentRepository.save(any(Student.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(put("/student").content(objectMapper.writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenStudentObject_whenDeleteStudentById_returnSuccessString() throws Exception {

        Student student = new Student("Test Name", null, new Date(20221128L), "testemail@gmail.com", "012345678910", "Test job role", null, ConsumerOrConsultant.CONSULTANT, null, false);

        given(studentRepository.findById(any(Integer.class))).willReturn(Optional.of(student));

        ResultActions response = mockMvc.perform(delete("/student/0"));

        response.andExpect(status().isNoContent())
                .andExpect(jsonPath("$", is("Successfully deleted student.")));
    }

    @Test
    public void givenStudentObject_whenDeleteStudentWithNoExistingStudentId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/student/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Instructor not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenStudentObject_whenDeleteStudentWithInvalidStudentId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/student/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
