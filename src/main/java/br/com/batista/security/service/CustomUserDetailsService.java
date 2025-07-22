package br.com.batista.security.service;

import br.com.batista.service.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService (UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        return new CustomUserDetails(userService.findByEmail(email));
    }
}
