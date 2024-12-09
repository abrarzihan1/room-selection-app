package hu.unideb.inf.roomselectionapp.SpringDataJpa.exception;

public class TeacherNotFoundException extends  RuntimeException{
    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
