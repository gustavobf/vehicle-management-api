package br.com.batista.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batista.dto.CarDTO;
import br.com.batista.service.CarService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/car")
public class CarController {

	@Autowired
	private CarService carService;

	@ApiOperation("Returns a list with all cars")
	@GetMapping
	public ResponseEntity<List<CarDTO>> getAll() {
		return ResponseEntity.status(200).body(carService.getAll());
	}

	@ApiOperation("Returns a car based on its id")
	@GetMapping("/{id}")
	public ResponseEntity<CarDTO> getById(@PathVariable final Long id) {
		return ResponseEntity.status(200).body(carService.getById(id).get());
	}

	@ApiOperation("Saves a car")
	@PostMapping
	public ResponseEntity<CarDTO> create(@RequestBody final CarDTO carroDTO) {
		return ResponseEntity.status(201).body(carService.create(carroDTO));
	}

	@ApiOperation("Deletes a car based on its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable final Long id) {
		carService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@ApiOperation("Updates a car based on its id")
	@PutMapping("/{id}")
	public ResponseEntity<CarDTO> update(@PathVariable final Long id, @RequestBody final CarDTO carroDTO) {
		final CarDTO dto = carService.update(id, carroDTO);
		return ResponseEntity.status(201).body(dto);
	}
}
