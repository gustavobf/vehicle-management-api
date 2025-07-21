package br.com.batista.dto.auth.response;

import java.util.*;

public record RegisterResponse(Long id, String username, String email, boolean active, Set<String> roles) {
}
