package hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.exception.TeacherNotFoundException;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.TeacherRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {


    TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public String createTeacher(Teacher teacher) {
          teacherRepository.save(teacher);
           return "Success";

    }

    @Override
    public String updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return "Success";
    }

    @Override
    public String deleteTeacher(String teacherId) {
        teacherRepository.deleteById(teacherId);
        return "Success";
    }

    @Override
    public Teacher getTeacher(String teacherId) {
        if(teacherRepository.findById(teacherId).isEmpty())
            throw new TeacherNotFoundException("Requested Teacher Does Not Exist ");
       return teacherRepository.findById(teacherId).get();

    }

    @Override
    public List<Teacher> getAllTeachers() {

        return teacherRepository.findAll();
    }
}
