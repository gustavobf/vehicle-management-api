package br.com.batista.dto.auth.response;

import java.util.Set;

public record LoginResponse(String token, Set<String> roles) {
}
