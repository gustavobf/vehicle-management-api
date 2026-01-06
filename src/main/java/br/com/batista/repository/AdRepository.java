package br.com.batista.repository;

import br.com.batista.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    Page<Ad> findAllByActiveTrue (Pageable pageable);
}
