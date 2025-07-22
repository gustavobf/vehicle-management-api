package br.com.batista.dto.auth.request;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Schema(description = "Username for the new user", example = "johndoe", required = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Schema(description = "Password for the new user", example = "password123", required = true)
    private String password;

    @Email(message = "Invalid email format")
    @Schema(description = "Email address of the new user", example = "janedoe@example.com", required = false)
    private String email;

    public RegisterRequest () {
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }
}
