package hu.unideb.inf.roomselectionapp.SpringDataJpa.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")

public class TeacherController {

    Teacher teacher;

    @GetMapping("{teacherId}")
    public Teacher getTeacher(@PathVariable("teacherId") String teacherId){
        return  teacher;
//                new Teacher("CS-001","Adamko Atilla Tamas","Adamk123@gmail.com","Computer Science");
    }


    @PostMapping
    public String createTeacher(@RequestBody Teacher teacher){
        this.teacher = teacher;
        return "Teacher added succesfully";
    }

    @PutMapping
    public String updateTeacher(@RequestBody Teacher teacher){
        this.teacher = teacher;
        return "Teacher Updadated Succesfully";
    }

    @DeleteMapping("{teacherId}")
    public String deleteTeacher(@PathVariable String teacherId){
        this.teacher = null;
        return "Teacher Removed Succesfully";
    }


}
