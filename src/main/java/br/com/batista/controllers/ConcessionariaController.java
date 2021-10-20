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

import br.com.batista.model.Concessionaria;
import br.com.batista.service.ConcessionariaService;

@RestController
@RequestMapping(value = "/api/concessionaria")
public class ConcessionariaController {

	@Autowired
	private ConcessionariaService concessionariaService;

	@GetMapping
	public ResponseEntity<List<Concessionaria>> getAll() {
		List<Concessionaria> list = concessionariaService.getAll();
		return ResponseEntity.status(200).body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Concessionaria> getById(@PathVariable Long id) {
		Optional<Concessionaria> concessionaria = concessionariaService.getById(id);
		return ResponseEntity.status(200).body(concessionaria.get());
	}

	@PostMapping
	public ResponseEntity<Concessionaria> create(@RequestBody Concessionaria concessionaria) {
		concessionaria = concessionariaService.create(concessionaria);
		return ResponseEntity.status(201).body(concessionaria);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		concessionariaService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Concessionaria> update(@PathVariable Long id,
			@RequestBody Concessionaria ConcessionariaNova) {
		Concessionaria concessionaria = concessionariaService.update(id, ConcessionariaNova);
		return ResponseEntity.status(201).body(concessionaria);
	}

}
