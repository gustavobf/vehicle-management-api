package br.com.batista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.entity.Model;
import org.springframework.stereotype.*;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
