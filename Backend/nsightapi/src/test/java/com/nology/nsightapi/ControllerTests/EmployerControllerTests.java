package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Controllers.EmployerController;
import com.nology.nsightapi.Entities.Employer;
import com.nology.nsightapi.Repositories.EmployerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.server.ResponseStatusException;

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

    @Test
    public void givenEmployerObject_whenGetEmployers_returnListOfEmployers() throws Exception {

        Employer employer = new Employer("Test name", null);
        given(employerRepository.findAll()).willReturn(List.of(employer));

        ResultActions response = mockMvc.perform(get("/employers"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(1)))
                .andExpect(jsonPath("$[0].id", is(employer.getId())))
                .andExpect(jsonPath("$[0].name", is(employer.getName())))
                .andExpect(jsonPath("$[0].students", is(employer.getStudents())));

    }

    @Test
    public void givenEmptyRepository_whenGetEmployers_returnEmptyList() throws Exception {

        ResultActions response = mockMvc.perform(get("/employers"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(0)));
    }

    @Test
    public void givenEmployerObject_whenGetEmployerById_returnEmployerObject() throws Exception {

        Employer employer = new Employer("Test name", null);
        given(employerRepository.findById(any(Integer.class))).willReturn(Optional.of(employer));

        ResultActions response = mockMvc.perform(get("/employer/0"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(employer.getId())))
                .andExpect(jsonPath("$.name", is(employer.getName())))
                .andExpect(jsonPath("$.students", is(employer.getStudents())));

    }

    @Test
    public void givenEmptyRepository_whenGetEmployerByInvalidId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(get("/employer/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Employer not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmptyRepository_whenGetEmployerByNonNumericId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(get("/employer/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmployerObject_whenCreateEmployer_returnSuccessString() throws Exception {

        Employer employer = new Employer("Test name", null);

        given(employerRepository.save(any(Employer.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/employer").content(objectMapper.writeValueAsString(employer)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully created new employer.")));
    }

    @Test
    public void givenEmployerObject_whenCreateEmployerWithExistingId_throwIdAlreadyExistsException() throws Exception {

        Employer employer = new Employer("Test name", null);

        given(employerRepository.findById(any(Integer.class))).willReturn(Optional.of(employer));

        ResultActions response = mockMvc.perform(post("/employer").content(objectMapper.writeValueAsString(employer)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("406 NOT_ACCEPTABLE \"Id already in use.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmployerObject_whenCreateEmployerWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Employer employer = new Employer(null, null);

        given(employerRepository.save(any(Employer.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(post("/employer").content(objectMapper.writeValueAsString(employer)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmployerObject_whenPutEmployer_returnSuccessString() throws Exception {

        Employer employer = new Employer("Test name", null);

        given(employerRepository.findById(any(Integer.class))).willReturn(Optional.of(employer));
        given(employerRepository.save(any(Employer.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/employer").content(objectMapper.writeValueAsString(employer)).contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully updated employer.")));
    }

    @Test
    public void givenEmployerObject_whenPutEmployerWithNoExistingEmployerId_returnNotFoundException() throws Exception {

        Employer employer = new Employer("Test name", null);

        given(employerRepository.save(any(Employer.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/employer").content(objectMapper.writeValueAsString(employer)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Employer not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmployerObject_whenPutEmployerWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Employer employer = new Employer("Test name", null);

        given(employerRepository.findById(any(Integer.class))).willReturn(Optional.of(employer));
        given(employerRepository.save(any(Employer.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(put("/employer").content(objectMapper.writeValueAsString(employer)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmployerObject_whenDeleteEmployerById_returnSuccessString() throws Exception {

        Employer employer = new Employer("Test name", null);

        given(employerRepository.findById(any(Integer.class))).willReturn(Optional.of(employer));

        ResultActions response = mockMvc.perform(delete("/employer/0"));

        response.andExpect(status().isNoContent())
                .andExpect(jsonPath("$", is("Successfully deleted employer.")));
    }

    @Test
    public void givenEmployerObject_whenDeleteEmployerWithNoExistingEmployerId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/employer/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Employer not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmployerObject_whenDeleteEmployerWithInvalidEmployerId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/employer/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
