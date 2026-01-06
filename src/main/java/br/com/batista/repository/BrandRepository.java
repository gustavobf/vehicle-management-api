package br.com.batista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.entity.Brand;
import org.springframework.stereotype.*;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
}
