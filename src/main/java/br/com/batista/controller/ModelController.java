package br.com.batista.controller;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.model.request.*;
import br.com.batista.dto.model.response.*;
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
@RequestMapping(value = "/api/model", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Model Controller", description = "Controller for managing model operations")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController (ModelService modelService) {
        this.modelService = modelService;
    }

    @Operation(summary = "Returns a list with all models")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/getall")
    public ResponseEntity<PageResponse<ModelResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getAll(pageable));
    }

    @Operation(summary = "Returns a model based on its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
            @ApiResponse(responseCode = "404", description = "Model not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @GetMapping("/getbyid")
    public ResponseEntity<ModelResponse> getById (
            @Parameter(description = "ID of the model to be retrieved", required = true) @RequestParam final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getById(id));
    }

    @Operation(summary = "Creates a model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Model created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<ModelResponse> create (@Valid @RequestBody final CreateModelRequest modelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.create(modelDTO));
    }

    @Operation(summary = "Deletes a model based on its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Model not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the model to be deleted", required = true) @RequestParam final Long id) {
        modelService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    @Operation(summary = "Updates a model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model updated successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Model not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PutMapping("/update")
    public ResponseEntity<ModelResponse> update (@Valid @RequestBody final UpdateModelRequest modelDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.update(modelDTO));
    }
}