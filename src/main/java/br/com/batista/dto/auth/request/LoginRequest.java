package br.com.batista.dto.auth.request;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must be at most 100 characters")
    @Schema(description = "Email of the user", example = "user@example.com", required = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Schema(description = "User's password", example = "password123", required = true)
    private String password;

    public LoginRequest () {
    }

    public LoginRequest (String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}