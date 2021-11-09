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

import br.com.batista.dto.MarcaDTO;
import br.com.batista.service.MarcaService;

@RestController
@RequestMapping(value = "/api/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@GetMapping
	public ResponseEntity<List<MarcaDTO>> getAll() {
		List<MarcaDTO> lista = marcaService.getAll();
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MarcaDTO> getById(@PathVariable Long id) {
		Optional<MarcaDTO> marca = marcaService.getById(id);
		return ResponseEntity.status(200).body(marca.get());
	}

	@PostMapping
	public ResponseEntity<MarcaDTO> create(@RequestBody MarcaDTO marcaDTO) {
		marcaDTO = marcaService.create(marcaDTO);
		return ResponseEntity.status(201).body(marcaDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		marcaService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<MarcaDTO> update(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO) {
		MarcaDTO dto = marcaService.update(id, marcaDTO);
		return ResponseEntity.status(201).body(dto);
	}

}
