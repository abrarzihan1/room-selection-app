package hu.unideb.inf.roomselectionapp.security.filter;

import hu.unideb.inf.roomselectionapp.security.model.User;
import hu.unideb.inf.roomselectionapp.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (isTokenPresent(header)) {
            String token = header.substring(TOKEN_PREFIX.length());

            if (jwtTokenUtil.validateToken(token)) {
                try {
                    Claims claims = jwtTokenUtil.getClaimsFromToken(token);
                    User user = createUserFromClaims(claims);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    logger.warn("Failed to parse JWT token", e);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isTokenPresent(String header) {
        return header != null && header.startsWith(TOKEN_PREFIX);
    }

    private User createUserFromClaims(Claims claims) {
        String[] subjectParts = claims.getSubject().split(",");
        User user = new User();

        user.setId(Integer.parseInt(subjectParts[0]));
        user.setUsername(subjectParts[1]);
        user.setRole(subjectParts[2]);

        return user;
    }
}
