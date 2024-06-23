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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/model", produces = { MediaType.APPLICATION_JSON_VALUE })
@Tag(name = "Model Controller", description = "Controller for managing model operations")
public class ModelController {

	private ModelService modelService;

	@Autowired
	public ModelController(ModelService modelService) {
		this.modelService = modelService;
	}

	@Operation(summary = "Returns a list with all models")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getall")
	public ResponseEntity<Page<ModelDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(modelService.getAll(pageable));
	}

	@Operation(summary = "Returns a model based on its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "404", description = "Model not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@GetMapping("/getbyid")
	public ResponseEntity<ModelDTO> getById(@RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(modelService.getById(id));
	}

	@Operation(summary = "Creates a model")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Model created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final ModelDTO modelDTO) {
		modelService.create(modelDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
	}

	@Operation(summary = "Deletes a model based on its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Model deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Model not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@RequestParam final Long id) {
		modelService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	@Operation(summary = "Updates a model")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Model updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "404", description = "Model not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final ModelDTO modelDTO) {
		modelService.update(modelDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}
}