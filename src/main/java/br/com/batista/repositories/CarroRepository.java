package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

}
