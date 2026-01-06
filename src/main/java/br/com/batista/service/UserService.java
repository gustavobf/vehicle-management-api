package br.com.batista.service;

import br.com.batista.entity.*;

public interface UserService {

    User findByEmail (String email);

    User getById (Long id);

    boolean existsByEmail (String email);

    User save (User user);
}
