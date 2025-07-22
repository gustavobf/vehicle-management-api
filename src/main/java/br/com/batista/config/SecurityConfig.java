package br.com.batista.config;

import br.com.batista.security.filter.*;
import br.com.batista.security.handler.*;
import br.com.batista.security.service.*;
import br.com.batista.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.cors.*;

import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final UserService userService;

    private static final String[] PUBLIC_URLS = {"/h2-console/**", "/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**",
            "/swagger-ui.html"};

    private static final String[] USER_URLS = {"/api/brand/**", "/api/model/**", "/api/dealership/**", "/api/order/**",
            "/api/ad/**", "/api/car/**"};

    @Autowired
    public SecurityConfig (UserService userService, JwtAuthFilter jwtAuthFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.userService = userService;
    }

    @Bean
    SecurityFilterChain filterChain (final HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        this.configureAuthorization(http);
        http.exceptionHandling(ex -> ex.authenticationEntryPoint(customAuthenticationEntryPoint));
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource () {
        final CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    private void configureAuthorization (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                requests -> requests.requestMatchers(PUBLIC_URLS).permitAll().requestMatchers(USER_URLS).hasRole("USER")
                        .anyRequest().authenticated());
    }


}
