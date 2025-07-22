package br.com.batista.dto.order.response;

import br.com.batista.dto.car.response.*;
import io.swagger.v3.oas.annotations.media.*;

import java.time.*;

public record OrderResponse(@Schema(description = "Order ID", example = "1") Long id,
                            @Schema(description = "Car", example = "10") CarResponse car,
                            @Schema(description = "Buyer", example = "2") String buyer,
                            @Schema(description = "Seller", example = "5") String seller,
                            @Schema(description = "Order Date", example = "2025-07-22T10:00:00") LocalDateTime orderDate,
                            @Schema(description = "Order Status", example = "PENDING") String status,
                            @Schema(description = "Total Price", example = "50000.0") Double totalPrice) {
}