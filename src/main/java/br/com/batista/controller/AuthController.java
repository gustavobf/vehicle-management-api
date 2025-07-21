package br.com.batista.controller;

import br.com.batista.dto.*;
import br.com.batista.entity.*;
import br.com.batista.repository.*;
import br.com.batista.utils.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Authentication Controller", description = "Endpoints for user authentication, including login and token management")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "User registration", description = "Register a new user with username, email and password", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RegisterRequest.class), examples = @ExampleObject(value = "{\"username\": \"johndoe\", \"email\": \"janedoe@example.com\", \"password\": \"password123\"}"))), responses = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Username already taken or validation error")})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRoles(Set.of("ROLE_USER"));
        user.setActive(true);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new br.com.batista.dto.ApiResponse("User registered successfully"));
    }

    @Operation(summary = "User login", description = "Authenticate user with username and password, returning a JWT token", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginRequest.class), examples = @ExampleObject(value = "{\"username\": \"johndoe\", \"password\": \"password123\"}"))), responses = {
            @ApiResponse(responseCode = "200", description = "Successful login, returns JWT token", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials or bad request")})
    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String token = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}