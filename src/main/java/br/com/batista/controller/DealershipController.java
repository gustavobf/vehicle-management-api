package br.com.batista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.dto.ResponseDto;
import br.com.batista.service.DealershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/dealership", produces = { MediaType.APPLICATION_JSON_VALUE })
@Tag(name = "Dealership Controller", description = "Controller for managing dealership operations")
public class DealershipController {

	private DealershipService dealershipService;

	@Autowired
	public DealershipController(DealershipService dealershipService) {
		this.dealershipService = dealershipService;
	}

	@Operation(summary = "Returns a list with all dealerships")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/getall")
	public ResponseEntity<Page<DealershipDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(dealershipService.getAll(pageable));
	}

	@Operation(summary = "Returns a dealership based on its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
			@ApiResponse(responseCode = "404", description = "Dealership not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/getbyid")
	public ResponseEntity<DealershipDTO> getById(
			@Parameter(description = "ID of the dealership to be retrieved", required = true) @RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(dealershipService.getById(id));
	}

	@Operation(summary = "Creates a dealership")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Dealership created successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final DealershipDTO dealershipDTO) {
		dealershipService.create(dealershipDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
	}

	@Operation(summary = "Deletes a dealership based on its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dealership deleted successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "Dealership not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(
			@Parameter(description = "ID of the dealership to be deleted", required = true) @RequestParam final Long id) {
		dealershipService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	@Operation(summary = "Updates a dealership")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Dealership updated successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Dealership not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final DealershipDTO dealershipDTO) {
		dealershipService.update(dealershipDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}
}