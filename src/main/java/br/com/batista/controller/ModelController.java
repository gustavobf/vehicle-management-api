package br.com.batista.controller;

import br.com.batista.constants.*;
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
import lombok.*;
import org.springdoc.core.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.MODEL_BASE_PATH)
@Tag(name = "Model Controller", description = "Controller for managing model operations")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @Operation(summary = "Returns a list with all models")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(ControllerConstants.GET_ALL)
    public ResponseEntity<PageResponse<ModelResponse>> getAll (
            @ParameterObject @PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getAll(pageable));
    }

    @Operation(summary = "Returns a model based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model found", content = @Content(schema = @Schema(implementation = ModelResponse.class))),
            @ApiResponse(responseCode = "404", description = "Model not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(ControllerConstants.GET_BY_ID)
    public ResponseEntity<ModelResponse> getById (
            @Parameter(description = "ID of the model to be retrieved", required = true) @RequestParam final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getById(id));
    }

    @Operation(summary = "Creates a model", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateModelRequest.class), examples = @ExampleObject(name = "Create model", value = """
            {
                "name": "Civic",
                "brand": "Honda"
            }
            """))), responses = {
            @ApiResponse(responseCode = "201", description = "Model created successfully", content = @Content(schema = @Schema(implementation = ModelResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(ControllerConstants.CREATE)
    public ResponseEntity<ModelResponse> create (@Valid @RequestBody final CreateModelRequest modelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.create(modelDTO));
    }

    @Operation(summary = "Deletes a model based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model deleted successfully", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Model not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping(ControllerConstants.DELETE)
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the model to be deleted", required = true) @RequestParam final Long id) {
        modelService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
    }

    @Operation(summary = "Updates a model", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateModelRequest.class), examples = @ExampleObject(name = "Update model", value = """
            {
                "id": 1,
                "name": "Civic LX",
                "brand": "Honda"
            }
            """))), responses = {
            @ApiResponse(responseCode = "200", description = "Model updated successfully", content = @Content(schema = @Schema(implementation = ModelResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Model not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PutMapping(ControllerConstants.UPDATE)
    public ResponseEntity<ModelResponse> update (@Valid @RequestBody final UpdateModelRequest modelDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.update(modelDTO));
    }
}
