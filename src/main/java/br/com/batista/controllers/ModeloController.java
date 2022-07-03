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

import br.com.batista.dto.ModeloDTO;
import br.com.batista.service.ModeloService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/modelo")
public class ModeloController {

	@Autowired
	private ModeloService modeloService;

	@ApiOperation("Returns a list with all models")
	@GetMapping
	public ResponseEntity<List<ModeloDTO>> getAll() {
		List<ModeloDTO> lista = modeloService.getAll();
		return ResponseEntity.status(200).body(lista);
	}

	@ApiOperation("Returns a model based on its id")
	@GetMapping("/{id}")
	public ResponseEntity<ModeloDTO> getById(@PathVariable Long id) {
		Optional<ModeloDTO> modelo = modeloService.getById(id);
		return ResponseEntity.status(200).body(modelo.get());
	}

	@ApiOperation("Saves a model")
	@PostMapping
	public ResponseEntity<ModeloDTO> create(@RequestBody ModeloDTO modeloDTO) {
		modeloDTO = modeloService.create(modeloDTO);
		return ResponseEntity.status(201).body(modeloDTO);
	}

	@ApiOperation("Deletes a model based on its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		modeloService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@ApiOperation("Updates a model based on its id")
	@PutMapping("/{id}")
	public ResponseEntity<ModeloDTO> update(@PathVariable Long id, @RequestBody ModeloDTO modeloDTO) {
		ModeloDTO dto = modeloService.update(id, modeloDTO);
		return ResponseEntity.status(201).body(dto);
	}
}
