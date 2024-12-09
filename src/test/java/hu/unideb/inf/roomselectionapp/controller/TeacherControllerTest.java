package hu.unideb.inf.roomselectionapp.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.controller.TeacherController;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
import hu.unideb.inf.roomselectionapp.security.util.JwtTokenUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TeacherController.class)
@TestPropertySource(properties = "security.jwt.enabled=false")
@AutoConfigureMockMvc(addFilters = false)
public class TeacherControllerTest {

    @MockBean
    private JwtTokenUtil  jwtTokenUtil;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    private Teacher teacher1;
    private Teacher teacher2;
    private List<Teacher> teacherList;

    @BeforeEach
    void setUp() {
        teacher1 = new Teacher("T001", "Dr. Alice Johnson", "alice.johnson@unideb.hu", "Mathematics");
        teacher2 = new Teacher("T002", "Dr. Bob Smith", "bob.smith@unideb.hu", "Physics");
        teacherList = new ArrayList<>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);
    }

    @AfterEach
    void tearDown() {
        teacherList.clear();
    }

    @Test
    void testGetTeacher() throws Exception {
        when(teacherService.getTeacher("T001")).thenReturn(teacher1);

        mockMvc.perform(get("/api/private/teacher/get/T001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teacherId").value("T001"))
                .andExpect(jsonPath("$.name").value("Dr. Alice Johnson"))
                .andExpect(jsonPath("$.email").value("alice.johnson@unideb.hu"))
                .andExpect(jsonPath("$.department").value("Mathematics"));
    }

    @Test
    void testGetAllTeachers() throws Exception {
        when(teacherService.getAllTeachers()).thenReturn(teacherList);

        mockMvc.perform(get("/api/private/teacher/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].teacherId").value("T001"))
                .andExpect(jsonPath("$[1].teacherId").value("T002"));
    }

    @Test
    void testCreateTeacher() throws Exception {
        // Mock behavior for createTeacher method to return a String response
        when(teacherService.createTeacher(any(Teacher.class))).thenReturn("Teacher added succesfully");

        // Perform HTTP POST request
        mockMvc.perform(post("/api/private/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "teacherId": "T001",
                                "name": "Dr. Alice Johnson",
                                "email": "alice.johnson@unideb.hu",
                                "department": "Mathematics"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(content().string("Teacher added succesfully"));
    }

    @Test
    void testUpdateTeacher() throws Exception {
        when(teacherService.updateTeacher(any(Teacher.class))).thenReturn("Teacher updated succesfully");

        mockMvc.perform(put("/api/private/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "teacherId": "T001",
                                    "name": "Dr. Alice Johnson",
                                    "email": "alice.johnson@unideb.hu",
                                    "department": "Mathematics"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("Teacher Updadated Succesfully"));
    }

    @Test
    void testDeleteTeacher() throws Exception {
        mockMvc.perform(delete("/api/private/teacher/T001"))
                .andExpect(status().isOk())
                .andExpect(content().string("Teacher Removed Succesfully"));
    }


}
