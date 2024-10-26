package hu.unideb.inf.roomselectionapp.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String username;
}
