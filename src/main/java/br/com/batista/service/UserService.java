package br.com.batista.service;

import br.com.batista.dto.auth.request.*;
import br.com.batista.dto.auth.response.*;
import br.com.batista.entity.*;
import br.com.batista.entity.User;
import br.com.batista.exception.*;
import br.com.batista.repository.*;
import br.com.batista.utils.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                        AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public RegisterResponse register (RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("User", "username", registerRequest.getUsername());
        }

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setActive(true);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleUser);

        user.addUserRole(userRole);

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(),
                savedUser.isActive(), savedUser.getRoleNames());

    }

    public LoginResponse login (@Valid LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String token = jwtUtil.generateToken(authentication);

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<String> roles = user.getRoleNames();

        return new LoginResponse(token, roles);
    }
}
