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

import br.com.batista.dto.BrandDTO;
import br.com.batista.dto.ResponseDto;
import br.com.batista.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/brand", produces = { MediaType.APPLICATION_JSON_VALUE })
@Tag(name = "Brand Controller", description = "Controller for managing brand operations")
public class BrandController {

	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@Operation(summary = "Returns a list with all brands")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/getall")
	public ResponseEntity<Page<BrandDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(brandService.getAll(pageable));
	}

	@Operation(summary = "Returns a brand based on its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the brand", content = @Content(schema = @Schema(implementation = BrandDTO.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Brand not found", content = @Content) })
	@GetMapping("/getbyid")
	public ResponseEntity<BrandDTO> getById(
			@Parameter(description = "ID of the brand to be retrieved", required = true) @RequestParam Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(brandService.getById(id));
	}

	@Operation(summary = "Creates a brand")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Brand created successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody BrandDTO brandDTO) {
		brandService.create(brandDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
	}

	@Operation(summary = "Deletes a brand based on its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Brand deleted successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Brand not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(
			@Parameter(description = "ID of the brand to be deleted", required = true) @RequestParam Long id) {
		brandService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	@Operation(summary = "Updates a brand")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Brand updated successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Brand not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody BrandDTO brandDTO) {
		brandService.update(brandDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}
}