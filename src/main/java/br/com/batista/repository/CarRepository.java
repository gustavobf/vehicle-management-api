package br.com.batista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.entity.Brand;
import br.com.batista.entity.Car;
import br.com.batista.entity.Dealership;
import br.com.batista.entity.Model;
import org.springframework.stereotype.*;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

	List<Car> findByBrand(Brand brand);

	List<Car> findByDealership(Dealership dealership);

	List<Car> findByModel(Model model);

}
