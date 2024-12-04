package hu.unideb.inf.roomselectionapp.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    Teacher teacher;


    @BeforeEach
    void setup(){
        teacher =new Teacher("CS-001","Adamko",
                "Adamko123@gmail.com","Computer Science");
        teacherRepository.save(teacher);
    }

    @AfterEach
    void tearDown(){
        teacher = null;
        teacherRepository.deleteAll();
    }


    @Test
    void testByTeacherName(){
        List<Teacher> teacherList = teacherRepository.findByName("Adamko");
        assertThat(teacherList.get(0).getName()).isEqualTo(teacher.getName());
    }

    @Test
    void testByTeacherTeacherId(){
        Optional<Teacher> teacherList = teacherRepository.findById("CS-001");
        assertThat(teacherList.get().getTeacherId()).isEqualTo(teacher.getTeacherId());
    }

}
