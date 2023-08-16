package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Dealership;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {

}
