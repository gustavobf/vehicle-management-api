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

import br.com.batista.model.Carro;
import br.com.batista.service.CarroService;

@RestController
@RequestMapping(value = "/api/carro")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<List<Carro>> getAll() {
		List<Carro> list = carroService.getAll();
		return ResponseEntity.status(200).body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carro> getById(@PathVariable Long id) {
		Optional<Carro> carro = carroService.getById(id);
		return ResponseEntity.status(200).body(carro.get());
	}

	@PostMapping
	public ResponseEntity<Carro> create(@RequestBody Carro carro) {
		carro = carroService.create(carro);
		return ResponseEntity.status(201).body(carro);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		carroService.delete(id);
		return ResponseEntity.status(204).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody Carro carroNovo) {
		Carro carro = carroService.update(id, carroNovo);
		return ResponseEntity.status(201).body(carro);
	}
}
