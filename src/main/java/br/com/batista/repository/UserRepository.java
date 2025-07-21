package br.com.batista.repository;

import br.com.batista.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);

    boolean existsByUsername (String username);

}
