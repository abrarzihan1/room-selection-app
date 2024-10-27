package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import hu.unideb.inf.roomselectionapp.security.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_info")
public class Teacher {
    @Id
    private String teacherId;
    private String name;
    private String email;
    private String department;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
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
}
