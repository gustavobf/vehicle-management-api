package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{

}
