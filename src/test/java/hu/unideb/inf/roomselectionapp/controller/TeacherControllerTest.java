package hu.unideb.inf.roomselectionapp.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.controller.TeacherController;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
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

}
