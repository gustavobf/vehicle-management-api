package br.com.batista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.entity.Dealership;
import org.springframework.stereotype.*;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {

}
