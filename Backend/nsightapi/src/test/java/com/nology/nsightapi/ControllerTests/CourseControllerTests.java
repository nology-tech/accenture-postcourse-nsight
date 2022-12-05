package com.nology.nsightapi.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.nsightapi.Controllers.CourseController;
import com.nology.nsightapi.Entities.Course;
import com.nology.nsightapi.Entities.CourseCategory;
import com.nology.nsightapi.Repositories.CourseRepository;
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

@WebMvcTest(CourseController.class)
public class CourseControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseRepository courseRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenCourseObject_whenGetCourses_returnListOfCourses() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );
        given(courseRepository.findAll()).willReturn(List.of(course));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/courses"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(1)))
                .andExpect(jsonPath("$[0].id", is(course.getId())))
                .andExpect(jsonPath("$[0].name", is(course.getName())))
                .andExpect(jsonPath("$[0].thumbnail", is(course.getThumbnail())))
                .andExpect(jsonPath("$[0].courseStart", is(dateFormatter.format(course.getCourseStart()))))
                .andExpect(jsonPath("$[0].courseFinish", is(course.getCourseFinish())))
                .andExpect(jsonPath("$[0].numberEnrolled", is(course.getNumberEnrolled())))
                .andExpect(jsonPath("$[0].numberEmployed", is(course.getNumberEmployed())))
                .andExpect(jsonPath("$[0].instructor", is(course.getInstructor())))
                .andExpect(jsonPath("$[0].courseCategory", is(String.valueOf(course.getCourseCategory()))))
                .andExpect(jsonPath("$[0].numberCompleted", is(course.getNumberCompleted())))
                .andExpect(jsonPath("$[0].description", is(course.getDescription())))
                .andExpect(jsonPath("$[0].active", is(course.isActive())))
                .andExpect(jsonPath("$[0].students", is(course.getStudents())));
    }

    @Test
    public void givenEmptyRepository_whenGetCourses_returnEmptyList() throws Exception {

        ResultActions response = mockMvc.perform(get("/courses"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)))
                .andExpect(jsonPath("size($)", is(0)));
    }

    @Test
    public void givenCourseObject_whenGetCourseById_returnCourseObject() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );
        given(courseRepository.findById(any(Integer.class))).willReturn(Optional.of(course));

        Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        ResultActions response = mockMvc.perform(get("/course/0"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(course.getId())))
                .andExpect(jsonPath("$.name", is(course.getName())))
                .andExpect(jsonPath("$.thumbnail", is(course.getThumbnail())))
                .andExpect(jsonPath("$.courseStart", is(dateFormatter.format(course.getCourseStart()))))
                .andExpect(jsonPath("$.courseFinish", is(course.getCourseFinish())))
                .andExpect(jsonPath("$.numberEnrolled", is(course.getNumberEnrolled())))
                .andExpect(jsonPath("$.numberEmployed", is(course.getNumberEmployed())))
                .andExpect(jsonPath("$.instructor", is(course.getInstructor())))
                .andExpect(jsonPath("$.courseCategory", is(String.valueOf(course.getCourseCategory()))))
                .andExpect(jsonPath("$.numberCompleted", is(course.getNumberCompleted())))
                .andExpect(jsonPath("$.description", is(course.getDescription())))
                .andExpect(jsonPath("$.active", is(course.isActive())))
                .andExpect(jsonPath("$.students", is(course.getStudents())));

    }

    @Test
    public void givenEmptyRepository_whenGetCourseByInvalidId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(get("/course/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Course not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenEmptyRepository_whenGetCourseByNonNumericId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(get("/course/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCourseObject_whenCreateCourse_returnSuccessString() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.save(any(Course.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/course").content(objectMapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully created new course.")));
    }

    @Test
    public void givenCourseObject_whenCreateCourseWithExistingId_throwIdAlreadyExistsException() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.findById(any(Integer.class))).willReturn(Optional.of(course));

        ResultActions response = mockMvc.perform(post("/course").content(objectMapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("406 NOT_ACCEPTABLE \"Id already in use.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCourseObject_whenCreateCourseWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.save(any(Course.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(post("/course").content(objectMapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCourseObject_whenPutCourse_returnSuccessString() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.findById(0)).willReturn(Optional.of(course));
        given(courseRepository.save(any(Course.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/course").content(objectMapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Successfully updated course.")));
    }

    @Test
    public void givenCourseObject_whenPutCourseWithNoExistingCourseId_returnNotFoundException() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.save(any(Course.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        ResultActions response = mockMvc.perform(put("/course").content(objectMapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Course not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCourseObject_whenPutCourseWithNullNonNullRequiredValues_throwDataIntegrityViolationException() throws Exception {

        Course course = new Course(null, 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.findById(any(Integer.class))).willReturn(Optional.of(course));
        given(courseRepository.save(any(Course.class))).willThrow(new DataIntegrityViolationException("Test msg"));

        ResultActions response = mockMvc.perform(put("/course").content(objectMapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Missing non-null values.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCourseObject_whenDeleteCourseById_returnSuccessString() throws Exception {

        Course course = new Course("Course Name", 2, 0, null, new Date(20221129L), null, CourseCategory.FULL_TIME, null, 1, "Test Description", false, null );

        given(courseRepository.findById(any(Integer.class))).willReturn(Optional.of(course));

        ResultActions response = mockMvc.perform(delete("/course/0"));

        response.andExpect(status().isNoContent())
                .andExpect(jsonPath("$", is("Successfully deleted course.")));
    }

    @Test
    public void givenCourseObject_whenDeleteCourseWithNoExistingCourseId_throwNotFoundException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/course/0"));

        response.andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("404 NOT_FOUND \"Course not found.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void givenCourseObject_whenDeleteCourseWithInvalidCourseId_throwBadRequestException() throws Exception {

        ResultActions response = mockMvc.perform(delete("/course/test"));

        response.andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Non-numeric id entered.\"", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
