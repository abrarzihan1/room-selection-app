package hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.TeacherRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;

import java.util.List;

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
       return teacherRepository.findById(teacherId).get();

    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}