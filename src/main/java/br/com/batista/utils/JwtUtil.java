package br.com.batista.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import javax.crypto.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private final long expirationMillis = 86400000;

    public String generateToken (Authentication authentication) {
        String username = authentication.getName();

        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername (String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid (String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired (String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

}
