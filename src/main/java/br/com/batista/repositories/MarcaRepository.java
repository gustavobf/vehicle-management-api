package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

}
