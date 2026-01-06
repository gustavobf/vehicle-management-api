package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.order.request.*;
import br.com.batista.dto.order.response.*;
import br.com.batista.entity.*;
import org.springframework.data.domain.*;

public interface OrderService {

    PageResponse<OrderResponse> getAll (Pageable pageable);

    PageResponse<OrderResponse> getAllByUser (Pageable pageable);

    Order getEntityById (Long id);

    OrderResponse getById (Long id);

    OrderResponse newOrder (CreateOrderRequest dto);

    void delete (Long id);
}
