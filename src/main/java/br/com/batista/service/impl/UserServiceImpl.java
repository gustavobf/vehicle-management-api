package br.com.batista.service.impl;

import br.com.batista.entity.User;
import br.com.batista.exception.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    public User save (User user) {
        return userRepository.save(user);
    }
}
