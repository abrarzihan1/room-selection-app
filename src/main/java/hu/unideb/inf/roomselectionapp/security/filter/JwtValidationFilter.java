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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenUtil.validateToken(token)) {
                Claims claims = jwtTokenUtil.getClaimsFromToken(token);
                String subject = claims.getSubject();
                String[] subArray = subject.split(",");

                User user = new User();
                user.setId(Integer.parseInt(subArray[0]));
                user.setUsername(subArray[1]);
                user.setRole(subArray[2]);

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
