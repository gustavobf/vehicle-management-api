package br.com.batista.security.handler;

import br.com.batista.dto.api.response.*;
import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.time.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence (HttpServletRequest request, HttpServletResponse response,
                          AuthenticationException authException) throws IOException {

        String message;

        if (authException instanceof InsufficientAuthenticationException) {
            message = "Authentication is required to access this resource.";
        } else if (authException instanceof BadCredentialsException) {
            message = "Invalid credentials provided.";
        } else {
            message = "Authentication failed: " + authException.getMessage();
        }

        ErrorResponseDto error = new ErrorResponseDto(request.getRequestURI(), HttpStatus.UNAUTHORIZED, message,
                LocalDateTime.now());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }

}
