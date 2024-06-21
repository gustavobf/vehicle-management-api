package br.com.batista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.entity.Brand;
import br.com.batista.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

	List<Car> findByBrand(Brand brand);

}
