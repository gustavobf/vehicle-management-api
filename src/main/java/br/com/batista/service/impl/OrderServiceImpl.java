package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.order.request.*;
import br.com.batista.dto.order.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.security.utils.*;
import br.com.batista.service.*;
import jakarta.transaction.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final AdService adService;

    public PageResponse<OrderResponse> getAll (Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        return new PageResponse<>(page.map(OrderMapper::mapToResponse));
    }

    public PageResponse<OrderResponse> getAllByUser (Pageable pageable) {
        Page<Order> page = orderRepository.findByBuyerId(SecurityUtils.getLoggedUserId(), pageable);
        return new PageResponse<>(page.map(OrderMapper::mapToResponse));
    }

    public Order getEntityById (Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", String.valueOf(id), "id"));
    }

    public OrderResponse getById (Long id) {
        return OrderMapper.mapToResponse(this.getEntityById(id));
    }

    @Transactional
    public OrderResponse newOrder (CreateOrderRequest dto) {
        try {
            Ad ad = adService.getEntityById(dto.getAdId());

            User buyer = userService.getById(SecurityUtils.getLoggedUserId());

            Order saved = orderRepository.save(Order.create(ad, buyer));

            return OrderMapper.mapToResponse(saved);
        } catch (Exception e) {
            logger.error("Error creating new order: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete (Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order", String.valueOf(id), "id"));
            orderRepository.delete(order);
        } catch (Exception e) {
            logger.error("Error deleting order with id {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
