package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
