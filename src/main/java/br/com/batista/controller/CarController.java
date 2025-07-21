package br.com.batista.controller;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springdoc.core.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/car", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Car Controller", description = "Controller for managing car operations")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController (CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Returns a list with all cars")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/getall")
    public ResponseEntity<PageResponse<CarResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAll(pageable));
    }

    @Operation(summary = "Returns a car based on its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/getbyid")
    public ResponseEntity<CarResponse> getById (
            @Parameter(description = "ID of the car to be retrieved", required = true) @RequestParam final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getById(id));
    }

    @Operation(summary = "Creates a car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<CarResponse> create (@Valid @RequestBody final CreateCarRequest carDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(carDTO));
    }

    @Operation(summary = "Deletes a car based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the car to be deleted", required = true) @RequestParam final Long id) {
        carService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    @Operation(summary = "Updates a car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PutMapping("/update")
    public ResponseEntity<CarResponse> update (@Valid @RequestBody final UpdateCarRequest carDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(carDTO));
    }
}