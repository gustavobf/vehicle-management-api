package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Concessionaria;

public interface ConcessionariaRepository extends JpaRepository<Concessionaria, Long> {

}
