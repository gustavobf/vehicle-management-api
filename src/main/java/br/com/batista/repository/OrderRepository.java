package br.com.batista.repository;

import br.com.batista.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByBuyerId(Long buyerId, Pageable pageable);

}
