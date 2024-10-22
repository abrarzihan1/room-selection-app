package hu.unideb.inf.roomselectionapp.SpringDataJpa.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")

public class TeacherController {



    TeacherService teacherService;

    Teacher teacher;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("{teacherId}")
    public Teacher getTeacher(@PathVariable("teacherId") String teacherId){
        return  teacherService.getTeacher(teacherId);
//                new Teacher("CS-001","Adamko Atilla Tamas","Adamk123@gmail.com","Computer Science");
    }

    @GetMapping()
    public List<Teacher> getAllTeachers(){
        return  teacherService.getAllTeachers();
    }


    @PostMapping
    public String createTeacher(@RequestBody Teacher teacher){
        teacherService.createTeacher(teacher);
        return "Teacher added succesfully";
    }

    @PutMapping
    public String updateTeacher(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "Teacher Updadated Succesfully";
    }

    @DeleteMapping("{teacherId}")
    public String deleteTeacher(@PathVariable String teacherId){
        teacherService.deleteTeacher(teacherId);
        return "Teacher Removed Succesfully";
    }


}
