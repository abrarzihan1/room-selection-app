package hu.unideb.inf.roomselectionapp.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.TeacherRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation.TeacherServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}
