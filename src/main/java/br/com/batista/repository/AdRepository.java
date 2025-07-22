package br.com.batista.repository;

import br.com.batista.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Page<Ad> findAllByActiveTrue(Pageable pageable);


}
