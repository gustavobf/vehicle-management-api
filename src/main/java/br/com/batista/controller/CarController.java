package br.com.batista.controller;

import br.com.batista.constants.*;
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
@RequestMapping(ControllerConstants.CAR_BASE_PATH)
@Tag(name = "Car Controller", description = "Controller for managing car operations")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController (CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Returns a list with all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(ControllerConstants.GET_ALL)
    public ResponseEntity<PageResponse<CarResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAll(pageable));
    }

    @Operation(summary = "Returns a car based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found", content = @Content(schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(ControllerConstants.GET_BY_ID)
    public ResponseEntity<CarResponse> getById (
            @Parameter(description = "ID of the car to be retrieved", required = true) @RequestParam final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getById(id));
    }

    @Operation(summary = "Creates a car", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateCarRequest.class), examples = @ExampleObject(name = "Create car", value = """
            {
                "name": "Corolla",
                "year": 2023,
                "price": 120000,
                "brandId": 1
            }
            """))), responses = {
            @ApiResponse(responseCode = "201", description = "Car created successfully", content = @Content(schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(ControllerConstants.CREATE)
    public ResponseEntity<CarResponse> create (@Valid @RequestBody final CreateCarRequest carDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(carDTO));
    }

    @Operation(summary = "Deletes a car based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car deleted successfully", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping(ControllerConstants.DELETE)
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the car to be deleted", required = true) @RequestParam final Long id) {
        carService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    @Operation(summary = "Updates a car", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateCarRequest.class), examples = @ExampleObject(name = "Update car", value = """
            {
                "id": 1,
                "name": "Corolla Altis",
                "year": 2024,
                "price": 130000,
                "brandId": 1
            }
            """))), responses = {
            @ApiResponse(responseCode = "200", description = "Car updated successfully", content = @Content(schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping(ControllerConstants.UPDATE)
    public ResponseEntity<CarResponse> update (@Valid @RequestBody final UpdateCarRequest carDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(carDTO));
    }
}
