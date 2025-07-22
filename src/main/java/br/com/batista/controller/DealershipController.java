package br.com.batista.controller;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
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
@RequestMapping(value = "/api/dealership", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Dealership Controller", description = "Controller for managing dealership operations")
public class DealershipController {

    private final DealershipService dealershipService;

    @Autowired
    public DealershipController (DealershipService dealershipService) {
        this.dealershipService = dealershipService;
    }

    @Operation(summary = "Returns a list with all dealerships")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/getall")
    public ResponseEntity<PageResponse<DealershipResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(dealershipService.getAll(pageable));
    }

    @Operation(summary = "Returns a dealership based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dealership found", content = @Content(schema = @Schema(implementation = DealershipResponse.class))),
            @ApiResponse(responseCode = "404", description = "Dealership not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/getbyid")
    public ResponseEntity<DealershipResponse> getById (
            @Parameter(description = "ID of the dealership to be retrieved", required = true) @RequestParam final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dealershipService.getById(id));
    }

    @Operation(summary = "Creates a dealership", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateDealershipRequest.class), examples = @ExampleObject(name = "Create dealership", value = """
            {
                "name": "AutoPrime",
                "cnpj": "12.345.678/0001-90"
            }
            """))), responses = {
            @ApiResponse(responseCode = "201", description = "Dealership created successfully", content = @Content(schema = @Schema(implementation = DealershipResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping("/create")
    public ResponseEntity<DealershipResponse> create (@Valid @RequestBody final CreateDealershipRequest dealershipDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dealershipService.create(dealershipDTO));
    }

    @Operation(summary = "Deletes a dealership based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dealership deleted successfully", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Dealership not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the dealership to be deleted", required = true) @RequestParam final Long id) {
        dealershipService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    @Operation(summary = "Updates a dealership", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateDealershipRequest.class), examples = @ExampleObject(name = "Update dealership", value = """
            {
                "id": 1,
                "name": "AutoPrime Motors",
                "cnpj": "12.345.678/0001-90"
            }
            """))), responses = {
            @ApiResponse(responseCode = "200", description = "Dealership updated successfully", content = @Content(schema = @Schema(implementation = DealershipResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Dealership not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping("/update")
    public ResponseEntity<DealershipResponse> update (@Valid @RequestBody final UpdateDealershipRequest dealershipDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(dealershipService.update(dealershipDTO));
    }
}
