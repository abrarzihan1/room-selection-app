package hu.unideb.inf.roomselectionapp.SpringDataJpa.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.datatransfer.FlavorListener;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,String >{

    List<Teacher> findByName(String name);

}
