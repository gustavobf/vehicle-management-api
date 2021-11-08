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

import br.com.batista.dto.ConcessionariaDTO;
import br.com.batista.service.ConcessionariaService;

@RestController
@RequestMapping(value = "/api/concessionaria")
public class ConcessionariaController {

	@Autowired
	private ConcessionariaService concessionariaService;

	@GetMapping
	public ResponseEntity<List<ConcessionariaDTO>> getAll() {
		List<ConcessionariaDTO> lista = concessionariaService.getAll();
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConcessionariaDTO> getById(@PathVariable Long id) {
		Optional<ConcessionariaDTO> concessionaria = concessionariaService.getById(id);
		return ResponseEntity.status(200).body(concessionaria.get());
	}

	@PostMapping
	public ResponseEntity<ConcessionariaDTO> create(@RequestBody ConcessionariaDTO concessionariaDTO) {
		concessionariaDTO = concessionariaService.create(concessionariaDTO);
		return ResponseEntity.status(201).body(concessionariaDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		concessionariaService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ConcessionariaDTO> update(@PathVariable Long id,
			@RequestBody ConcessionariaDTO concessionariaDTO) {
		ConcessionariaDTO dto = concessionariaService.update(id, concessionariaDTO);
		return ResponseEntity.status(201).body(dto);
	}

}
