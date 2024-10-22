package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

public class Teacher {
    private String  teacherId;
    private String name;
    private String email;
    private String department;

    public Teacher() {
    }

    public Teacher(String teacherId, String name, String email, String department) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.department = department;
    }

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
