package br.com.batista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.batista.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
