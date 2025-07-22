package br.com.batista.security.service;

import br.com.batista.dto.auth.request.*;
import br.com.batista.dto.auth.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.service.*;
import br.com.batista.utils.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthService (RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authManager,
                        JwtUtil jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
    }

    public LoginResponse login (LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String token = jwtUtil.generateToken(authentication);

        User user = userService.findByEmail(loginRequest.getEmail());

        Set<String> roles = user.getRoleNames();

        return new LoginResponse(token, roles);
    }

    public RegisterResponse register (RegisterRequest registerRequest) {

        if (userService.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("User", "email", registerRequest.getEmail());
        }

        Role roleUser = roleService.findByName("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setActive(true);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleUser);

        user.addUserRole(userRole);

        User savedUser = userService.save(user);

        return new RegisterResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(),
                savedUser.isActive(), savedUser.getRoleNames());

    }

}

