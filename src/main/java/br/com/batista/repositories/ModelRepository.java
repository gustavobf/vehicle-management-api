package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Modelo;

public interface ModelRepository extends JpaRepository<Modelo, Long> {

}
