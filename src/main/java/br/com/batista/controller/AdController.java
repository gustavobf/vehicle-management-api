package br.com.batista.controller;

import br.com.batista.dto.ad.request.*;
import br.com.batista.dto.ad.response.*;
import br.com.batista.dto.api.response.*;
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
@RequestMapping(value = "/api/ad", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Ad Controller", description = "Controller for managing car advertisements")
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController (AdService adService) {
        this.adService = adService;
    }

    @Operation(summary = "Returns a list with all active ads")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/getall")
    public ResponseEntity<PageResponse<AdResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "price") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(adService.getAllActive(pageable));
    }

    @Operation(summary = "Returns an ad based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ad", content = @Content(schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Ad not found")})
    @GetMapping("/getbyid")
    public ResponseEntity<AdResponse> getById (
            @Parameter(description = "ID of the ad to be retrieved", required = true) @RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adService.getById(id));
    }

    @Operation(summary = "Creates a new ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ad created successfully", content = @Content(schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping("/create")
    public ResponseEntity<AdResponse> create (
            @Parameter(description = "Ad data to create", required = true) @Valid @RequestBody CreateAdRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.create(request));
    }

    @Operation(summary = "Deletes an ad by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad deleted successfully", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Ad not found")})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the ad to be deleted", required = true) @RequestParam Long id) {
        adService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), "Ad deleted successfully"));
    }

    @Operation(summary = "Updates an existing ad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ad updated successfully", content = @Content(schema = @Schema(implementation = AdResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Ad not found")})
    @PutMapping("/update")
    public ResponseEntity<AdResponse> update (
            @Parameter(description = "Ad data to update", required = true) @Valid @RequestBody UpdateAdRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(adService.update(request));
    }
}
