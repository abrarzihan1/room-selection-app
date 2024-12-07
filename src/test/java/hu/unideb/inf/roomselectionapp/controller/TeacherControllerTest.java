package hu.unideb.inf.roomselectionapp.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.controller.TeacherController;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
import hu.unideb.inf.roomselectionapp.security.util.JwtTokenUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TeacherController.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;
    Teacher teacher1;
    Teacher teacher2;
    List<Teacher> teacherList = new ArrayList<>();
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        Teacher teacher1 = new Teacher("T001", "Dr. Alice Johnson", "alice.johnson@unideb.hu", "Mathematics");
        Teacher teacher2 = new Teacher("T002", "Dr. Bob Smith", "bob.smith@unideb.hu", "Physics");
        teacherList.add(teacher1);
        teacherList.add(teacher2);
    }

    @AfterEach
    void tearDown() {
        teacherList.clear();
    }


}

