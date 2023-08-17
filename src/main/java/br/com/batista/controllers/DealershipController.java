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

import br.com.batista.dto.DealershipDTO;
import br.com.batista.service.DealershipService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/dealership")
public class DealershipController {

	@Autowired
	private DealershipService dealershipService;

	@ApiOperation("Returns a list with all dealerships")
	@GetMapping
	public ResponseEntity<List<DealershipDTO>> getAll() {
		return ResponseEntity.status(200).body(dealershipService.getAll());
	}

	@ApiOperation("Returns a dealership based on its id")
	@GetMapping("/{id}")
	public ResponseEntity<DealershipDTO> getById(@PathVariable final Long id) {
		return ResponseEntity.status(200).body(dealershipService.getById(id).get());
	}

	@ApiOperation("Saves a dealership")
	@PostMapping
	public ResponseEntity<DealershipDTO> create(@RequestBody final DealershipDTO dealershipDTO) {
		return ResponseEntity.status(201).body(dealershipService.create(dealershipDTO));
	}

	@ApiOperation("Deletes a dealership based on its id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable final Long id) {
		dealershipService.delete(id);
		return ResponseEntity.status(204).build();
	}

	@ApiOperation("Updates a dealership")
	@PutMapping
	public ResponseEntity<DealershipDTO> update(@RequestBody final DealershipDTO dealershipDTO) {
		final DealershipDTO dto = dealershipService.update(dealershipDTO);
		return ResponseEntity.status(200).body(dto);
	}

}
