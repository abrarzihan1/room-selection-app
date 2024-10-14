package hu.unideb.inf.roomselectionapp.data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private int teacherId;
    private String name;
    private String email;
    private String department ;




    public Teacher(String name, String email, String department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId && Objects.equals(name, teacher.name) && Objects.equals(email, teacher.email) && Objects.equals(department, teacher.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, name, email, department);
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }



}
