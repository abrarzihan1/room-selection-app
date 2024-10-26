package hu.unideb.inf.roomselectionapp.security.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequest {
    private String username;
    private String password;
}
