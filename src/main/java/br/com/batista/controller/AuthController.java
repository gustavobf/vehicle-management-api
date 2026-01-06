package br.com.batista.controller;

import br.com.batista.constants.*;
import br.com.batista.dto.auth.request.*;
import br.com.batista.dto.auth.response.*;
import br.com.batista.security.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.AUTH_BASE_PATH)
@Tag(name = "Authentication Controller", description = "Endpoints for user authentication, including login and token management")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "User registration", description = "Register a new user with username, email and password", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RegisterRequest.class), examples = @ExampleObject(name = "Example request", value = """
            {
                "username": "johndoe",
                "email": "janedoe@example.com",
                "password": "password123"
            }
            """))), responses = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Username already taken or validation error")})
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser (@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }

    @Operation(summary = "User login", description = "Authenticate user with username and password, returning a JWT token", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginRequest.class), examples = @ExampleObject(name = "Example login", value = """
            {
                "username": "johndoe",
                "password": "password123"
            }
            """))), responses = {
            @ApiResponse(responseCode = "200", description = "Successful login, returns JWT token", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials or bad request")})
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
