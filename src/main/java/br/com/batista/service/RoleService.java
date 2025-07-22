package br.com.batista.service;

import br.com.batista.entity.*;
import br.com.batista.repository.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
    }

}
