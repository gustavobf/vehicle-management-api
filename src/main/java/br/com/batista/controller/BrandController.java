package br.com.batista.controller;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
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
@RequestMapping(value = "/api/brand", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Brand Controller", description = "Controller for managing brand operations")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController (BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "Returns a list with all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/getall")
    public ResponseEntity<PageResponse<BrandResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.getAll(pageable));
    }

    @Operation(summary = "Returns a brand based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the brand", content = @Content(schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Brand not found")})
    @GetMapping("/getbyid")
    public ResponseEntity<BrandResponse> getById (
            @Parameter(description = "ID of the brand to be retrieved", required = true) @RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.getById(id));
    }

    @Operation(summary = "Creates a brand", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateBrandRequest.class), examples = @ExampleObject(name = "Create brand", value = """
            {
                "name": "Toyota"
            }
            """))), responses = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully", content = @Content(schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping("/create")
    public ResponseEntity<BrandResponse> create (@Valid @RequestBody CreateBrandRequest brandDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.create(brandDTO));
    }

    @Operation(summary = "Deletes a brand based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand deleted successfully", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the brand to be deleted", required = true) @RequestParam Long id) {
        brandService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    @Operation(summary = "Updates a brand", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateBrandRequest.class), examples = @ExampleObject(name = "Update brand", value = """
            {
                "id": 1,
                "name": "Nissan"
            }
            """))), responses = {
            @ApiResponse(responseCode = "200", description = "Brand updated successfully", content = @Content(schema = @Schema(implementation = BrandResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping("/update")
    public ResponseEntity<BrandResponse> update (@Valid @RequestBody UpdateBrandRequest brandDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(brandService.update(brandDTO));
    }
}
