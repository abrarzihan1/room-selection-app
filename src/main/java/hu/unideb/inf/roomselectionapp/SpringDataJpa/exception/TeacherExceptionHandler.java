package hu.unideb.inf.roomselectionapp.SpringDataJpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TeacherExceptionHandler {

    @ExceptionHandler(value = TeacherNotFoundException.class)
    public ResponseEntity<Object> handleTeacherNotFoundException(TeacherNotFoundException teacherNotFoundException){
        TeacherException teacherException = new TeacherException(
                teacherNotFoundException.getMessage(),
                teacherNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(teacherException, HttpStatus.NOT_FOUND);
    }
}
