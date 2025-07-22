package br.com.batista.security.filter;

import br.com.batista.security.service.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    private UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthFilter (UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwt).getBody();
            List<String> roles = Optional.ofNullable(claims.get("roles", List.class)).orElse(Collections.emptyList());
            String username = claims.getSubject();

            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private SecretKey getSigningKey () {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

}
