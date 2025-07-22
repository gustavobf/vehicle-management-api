package br.com.batista.security.service;

import br.com.batista.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails (User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getRoleNames().stream().map(role -> (GrantedAuthority) () -> role)
                .collect(Collectors.toList());
    }

    public Long getId () {
        return id;
    }

    public String getEmail () {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return authorities;
    }

    @Override
    public String getPassword () {
        return password;
    }

    @Override
    public String getUsername () {
        return email;
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}
