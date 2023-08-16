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

import br.com.batista.dto.BrandDTO;
import br.com.batista.service.BrandService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@ApiOperation("Returns a list with all brands")
	@GetMapping
	public ResponseEntity<List<BrandDTO>> getAll() {
		return ResponseEntity.status(200).body(brandService.getAll());
	}

	@ApiOperation("Returns a brand based on its id")
	@GetMapping("/{id}")
	public ResponseEntity<BrandDTO> getById(@PathVariable final Long id) {
		return ResponseEntity.status(200).body(brandService.getById(id).get());
	}

	@ApiOperation("Saves a brand")
	@PostMapping
	public ResponseEntity<BrandDTO> create(@RequestBody final BrandDTO marcaDTO) {
		return ResponseEntity.status(201).body(brandService.create(marcaDTO));
	}

	@ApiOperation("Deletes a brand based on its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable final Long id) {
		brandService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@ApiOperation("Updates a brand based on its id")
	@PutMapping("/{id}")
	public ResponseEntity<BrandDTO> update(@PathVariable final Long id, @RequestBody final BrandDTO marcaDTO) {
		final BrandDTO dto = brandService.update(id, marcaDTO);
		return ResponseEntity.status(201).body(dto);
	}

}
