package br.com.batista.service.impl;

import br.com.batista.entity.User;
import br.com.batista.exception.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import jakarta.transaction.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public User findByEmail (String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id), "id"));
    }

    public boolean existsByEmail (String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public User save (User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw e;
        }
    }
}