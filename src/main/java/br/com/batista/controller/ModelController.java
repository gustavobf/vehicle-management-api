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

import br.com.batista.dto.ModelDTO;
import br.com.batista.dto.ResponseDto;
import br.com.batista.service.ModelService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/model", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ModelController {

	private ModelService modelService;

	@Autowired
	public ModelController(ModelService modelService) {
		this.modelService = modelService;
	}

	@ApiOperation("Returns a list with all models")
	@GetMapping("/getall")
	public ResponseEntity<Page<ModelDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(modelService.getAll(pageable));
	}

	@ApiOperation("Returns a model based on its id")
	@GetMapping("/getbyid")
	public ResponseEntity<ModelDTO> getById(@RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(modelService.getById(id));
	}

	@ApiOperation("Saves a model")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final ModelDTO modelDTO) {
		modelService.create(modelDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
	}

	@ApiOperation("Deletes a model based on its id")
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@RequestParam final Long id) {
		modelService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}

	@ApiOperation("Updates a model")
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final ModelDTO modelDTO) {
		modelService.update(modelDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}
}
