package hu.unideb.inf.roomselectionapp.SpringDataJpa.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;

import java.util.List;
import java.util.SplittableRandom;

public interface TeacherService {
    public String createTeacher(Teacher teacher);
    public String updateTeacher(Teacher teacher);
    public String deleteTeacher(String teacherId);
    public Teacher getTeacher(String teacherId);
    public List<Teacher> getAllTeachers();

}
