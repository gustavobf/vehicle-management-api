package br.com.batista.mapper;

import br.com.batista.dto.order.response.*;
import br.com.batista.entity.*;

public class OrderMapper {

    public static OrderResponse mapToResponse (Order order) {
        return new OrderResponse(order.getId(), CarMapper.mapToCarResponseDTO(order.getAd().getCar()),
                order.getBuyer().getUsername(), order.getAd().getUser().getUsername(), order.getOrderDate(),
                order.getStatus(), order.getTotalPrice());
    }

}