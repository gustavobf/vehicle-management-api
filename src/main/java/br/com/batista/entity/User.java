package br.com.batista.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    public User () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Set<String> getRoles () {
        return roles;
    }

    public void setRoles (Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public boolean isActive () {
        return active;
    }

    public void setActive (boolean active) {
        this.active = active;
    }
}
