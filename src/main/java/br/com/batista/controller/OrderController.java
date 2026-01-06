package br.com.batista.controller;

import br.com.batista.constants.*;
import br.com.batista.dto.api.response.*;
import br.com.batista.dto.order.request.*;
import br.com.batista.dto.order.response.*;
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
@RequestMapping(ControllerConstants.ORDER_BASE_PATH)
@Tag(name = "Order Controller", description = "Controller for managing orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Returns a paginated list with all orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(ControllerConstants.GET_ALL)
    public ResponseEntity<PageResponse<OrderResponse>> getAll (@ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllByUser(pageable));
    }

    @Operation(summary = "Returns an order based on its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order found", content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")})
    @GetMapping(ControllerConstants.GET_BY_ID)
    public ResponseEntity<OrderResponse> getById (
            @Parameter(description = "ID of the order to be retrieved", required = true) @RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getById(id));
    }

    @Operation(summary = "Creates a new order", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateOrderRequest.class), examples = @ExampleObject(name = "Create order", value = """
            {
                "carId": 3,
                "quantity": 1,
                "paymentMethod": "CREDIT_CARD",
                "deliveryAddress": "123 Example Street, City, Country"
            }
            """))), responses = {
            @ApiResponse(responseCode = "201", description = "Order created successfully", content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(ControllerConstants.CREATE)
    public ResponseEntity<OrderResponse> create (@Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.newOrder(request));
    }

    @Operation(summary = "Deletes an order by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order deleted successfully", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")})
    @DeleteMapping(ControllerConstants.DELETE)
    public ResponseEntity<ResponseDto> delete (
            @Parameter(description = "ID of the order to be deleted", required = true) @RequestParam Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.value(), "Order deleted successfully"));
    }
}

