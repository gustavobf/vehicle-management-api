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

import br.com.batista.dto.CarDTO;
import br.com.batista.dto.RequestCarDTO;
import br.com.batista.dto.ResponseCarDTO;
import br.com.batista.dto.ResponseDto;
import br.com.batista.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/car", produces = { MediaType.APPLICATION_JSON_VALUE })
@Tag(name = "Car Controller", description = "Controller for managing car operations")
public class CarController {

	private CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@Operation(summary = "Returns a list with all cars")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/getall")
	public ResponseEntity<Page<ResponseCarDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(carService.getAll(pageable));
	}

	@Operation(summary = "Returns a car based on its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
			@ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@GetMapping("/getbyid")
	public ResponseEntity<ResponseCarDTO> getById(
			@Parameter(description = "ID of the car to be retrieved", required = true) @RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(carService.getById(id));
	}

	@Operation(summary = "Creates a car")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Car created successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final RequestCarDTO carDTO) {
		carService.create(carDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
	}

	@Operation(summary = "Deletes a car based on its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Car deleted successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@Parameter(description = "ID of the car to be deleted", required = true)@RequestParam final Long id) {
		carService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	@Operation(summary = "Updates a car")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Car updated successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final CarDTO carDTO) {
		carService.update(carDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}
}