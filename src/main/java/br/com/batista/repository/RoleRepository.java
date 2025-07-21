package br.com.batista.repository;

import br.com.batista.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (String name);
}
