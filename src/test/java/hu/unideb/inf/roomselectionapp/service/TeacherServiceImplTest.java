package hu.unideb.inf.roomselectionapp.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.TeacherRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation.TeacherServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class TeacherServiceImplTest {
    @Mock
    private TeacherRepository teacherRepository;
    private TeacherService teacherService;
    AutoCloseable autoCloseable;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        teacherService = new TeacherServiceImpl(teacherRepository);
        teacher = new Teacher("R001", "Adamko", "adamko123@gmail.com","Computer Science");

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();

    }

    @Test
    void testCreateTeacher() {
        mock(Teacher.class);    // redundant
        mock(TeacherServiceImpl.class); //redundant

        when(teacherRepository.save(teacher)).thenReturn(teacher);
        assertThat(teacherService.createTeacher(teacher))
                .isEqualTo("Success");
    }

    @Test
    void testUpdateTeacher() {
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        assertThat(teacherService.updateTeacher(teacher)).isEqualTo("Success");
    }

    @Test
    void testGetTeacher() {
        when(teacherRepository.findById("RO01")).thenReturn(Optional.ofNullable(teacher));
        assertThat(teacherService.getTeacher("RO01").getName()).isEqualTo("Adamko");

    }

    @Test
    void testGetAllTeachers() {
        when(teacherRepository.findAll()).thenReturn( new ArrayList<Teacher>(Collections.singletonList(teacher)));
        assertThat(teacherService.getAllTeachers().get(0).getName()).isEqualTo("Adamko");

    }

    @Test
    void testDeleteTeacher() {
        mock(Teacher.class);
        mock(TeacherRepository.class, Mockito.CALLS_REAL_METHODS);

        doNothing().when(teacherRepository).deleteById(any());

        assertThat(teacherService.deleteTeacher("RO01")).isEqualTo("Success");
    }
}

