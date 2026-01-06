package br.com.batista.repository;

import br.com.batista.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail (String email);

    boolean existsByEmail (String username);

}
