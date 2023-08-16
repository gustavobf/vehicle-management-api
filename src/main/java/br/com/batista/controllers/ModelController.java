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

import br.com.batista.dto.ModelDTO;
import br.com.batista.service.ModelService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/model")
public class ModelController {

	@Autowired
	private ModelService modelService;

	@ApiOperation("Returns a list with all models")
	@GetMapping
	public ResponseEntity<List<ModelDTO>> getAll() {
		return ResponseEntity.status(200).body(modelService.getAll());
	}

	@ApiOperation("Returns a model based on its id")
	@GetMapping("/{id}")
	public ResponseEntity<ModelDTO> getById(@PathVariable final Long id) {
		return ResponseEntity.status(200).body(modelService.getById(id).get());
	}

	@ApiOperation("Saves a model")
	@PostMapping
	public ResponseEntity<ModelDTO> create(@RequestBody final ModelDTO modeloDTO) {
		return ResponseEntity.status(201).body(modelService.create(modeloDTO));
	}

	@ApiOperation("Deletes a model based on its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable final Long id) {
		modelService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@ApiOperation("Updates a model based on its id")
	@PutMapping("/{id}")
	public ResponseEntity<ModelDTO> update(@PathVariable final Long id, @RequestBody final ModelDTO modeloDTO) {
		final ModelDTO dto = modelService.update(id, modeloDTO);
		return ResponseEntity.status(201).body(dto);
	}
}
