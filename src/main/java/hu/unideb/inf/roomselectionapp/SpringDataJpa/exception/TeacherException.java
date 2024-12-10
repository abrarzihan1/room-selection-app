package hu.unideb.inf.roomselectionapp.SpringDataJpa.exception;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Teacher;
import org.springframework.http.HttpStatus;


public class TeacherException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public TeacherException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }


    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
