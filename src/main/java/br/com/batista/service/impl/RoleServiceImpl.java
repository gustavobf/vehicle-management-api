package br.com.batista.service.impl;

import br.com.batista.entity.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public Role findByName (String name) {
        return roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role USER not found"));
    }

}
