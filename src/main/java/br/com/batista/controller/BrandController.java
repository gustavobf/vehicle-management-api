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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/brand", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BrandController {

	private BrandService brandService;

	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@ApiOperation("Returns a list with all brands")
	@GetMapping("/getall")
	public ResponseEntity<Page<BrandDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(brandService.getAll(pageable));
	}

	@ApiOperation("Returns a brand based on its id")
	@GetMapping("/getbyid")
	public ResponseEntity<BrandDTO> getById(@RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(brandService.getById(id));
	}

	@ApiOperation("Saves a brand")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final BrandDTO brandDTO) {
		brandService.create(brandDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));

	}

	@ApiOperation("Deletes a brand based on its id")
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@RequestParam final Long id) {
		brandService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	@ApiOperation("Updates a brand")
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final BrandDTO brandDTO) {
		brandService.update(brandDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}

}
