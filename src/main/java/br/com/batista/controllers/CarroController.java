package br.com.batista.controllers;

import java.util.List;
import java.util.Optional;

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

import br.com.batista.dto.CarroDTO;
import br.com.batista.service.CarroService;

@RestController
@RequestMapping(value = "/api/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<List<CarroDTO>> getAll() {
		List<CarroDTO> lista = carroService.getAll();
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> getById(@PathVariable Long id) {
		Optional<CarroDTO> carro = carroService.getById(id);
		return ResponseEntity.status(200).body(carro.get());
	}

	@PostMapping
	public ResponseEntity<CarroDTO> create(@RequestBody CarroDTO carroDTO) {
		carroDTO = carroService.create(carroDTO);
		return ResponseEntity.status(201).body(carroDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		carroService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarroDTO> update(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
		CarroDTO dto = carroService.update(id, carroDTO);
		return ResponseEntity.status(201).body(dto);
	}
}
