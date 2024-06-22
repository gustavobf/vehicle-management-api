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
import br.com.batista.entity.Brand;
import br.com.batista.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Brands", description = "CRUD operations for Brand")
@RestController
@RequestMapping(value = "/api/brand", produces = { MediaType.APPLICATION_JSON_VALUE })
public class BrandController {

	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@Operation(summary = "Returns a list with all brands")
	@GetMapping("/getall")
	public ResponseEntity<Page<BrandDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(brandService.getAll(pageable));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the brand", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Brand.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Brand not found", content = @Content) })
	@Operation(summary = "Returns a brand based on its id")
	@GetMapping("/getbyid")
	public ResponseEntity<BrandDTO> getById(
			@Parameter(description = "id of brand to be searched") @RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(brandService.getById(id));
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Brand created successfully", content = @Content) })
	@Operation(summary = "Creates a brand")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final BrandDTO brandDTO) {
		brandService.create(brandDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));

	}

	@Operation(summary = "Deletes a brand based on its id")
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@RequestParam final Long id) {
		brandService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	@Operation(summary = "Updates a brand")
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final BrandDTO brandDTO) {
		brandService.update(brandDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}

}
