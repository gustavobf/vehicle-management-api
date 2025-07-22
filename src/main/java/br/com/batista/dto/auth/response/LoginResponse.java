package br.com.batista.dto.auth.response;

import io.swagger.v3.oas.annotations.media.*;

import java.util.Set;

public record LoginResponse(
        @Schema(description = "JWT authentication token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...") String token,

        @Schema(description = "Roles assigned to the user", example = "[\"USER\", \"ADMIN\"]") Set<String> roles) {
}