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

@RestController
@RequestMapping(value = "/api/car", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CarController {

	private CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	//@Operation("Returns a list with all cars")
	@GetMapping("/getall")
	public ResponseEntity<Page<ResponseCarDTO>> getAll(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(carService.getAll(pageable));
	}

	//@Operation("Returns a car based on its id")
	@GetMapping("/getbyid")
	public ResponseEntity<ResponseCarDTO> getById(@RequestParam final Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(carService.getById(id));
	}

	//@Operation("Saves a car")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> create(@RequestBody final RequestCarDTO carDTO) {
		carService.create(carDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));

	}

	//@Operation("Deletes a car based on its id")
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> delete(@RequestParam final Long id) {
		carService.delete(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
	}

	//@Operation("Updates a car")
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> update(@RequestBody final CarDTO carDTO) {
		carService.update(carDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));

	}
}
