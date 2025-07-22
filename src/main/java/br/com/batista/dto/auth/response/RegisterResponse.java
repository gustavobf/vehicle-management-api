package br.com.batista.dto.auth.response;

import io.swagger.v3.oas.annotations.media.*;

import java.util.*;


public record RegisterResponse(@Schema(description = "Unique identifier of the registered user", example = "1") Long id,

                               @Schema(description = "Username of the registered user", example = "johndoe") String username,

                               @Schema(description = "Email of the registered user", example = "johndoe@example.com") String email,

                               @Schema(description = "Indicates if the user account is active", example = "true") boolean active,

                               @Schema(description = "Roles assigned to the user", example = "[\"USER\", \"ADMIN\"]") Set<String> roles) {
}
