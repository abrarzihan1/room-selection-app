package hu.unideb.inf.roomselectionapp.security.controller;

import hu.unideb.inf.roomselectionapp.security.model.AuthRequest;
import hu.unideb.inf.roomselectionapp.security.model.AuthResponse;
import hu.unideb.inf.roomselectionapp.security.model.SignupRequest;
import hu.unideb.inf.roomselectionapp.security.model.User;
import hu.unideb.inf.roomselectionapp.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            Authentication authenticatedUser = authenticationManager.authenticate(authentication);
            User user = (User) authenticatedUser.getPrincipal();
            String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(token, user.getUsername()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        // Not completed
        return null;
    }
}
