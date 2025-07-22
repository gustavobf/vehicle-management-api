package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.order.request.*;
import br.com.batista.dto.order.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.security.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CarService carService;
    private final UserService userService;
    private final AdService adService;

    @Autowired
    public OrderService (AdService adService, OrderRepository orderRepository, CarService carService,
                         UserService userService) {
        this.orderRepository = orderRepository;
        this.carService = carService;
        this.userService = userService;
        this.adService = adService;
    }

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

    public OrderResponse newOrder (CreateOrderRequest dto) {

        Ad ad = adService.getEntityById(dto.getAdId());

        User buyer = userService.getById(SecurityUtils.getLoggedUserId());

        Order saved = orderRepository.save(Order.create(ad, buyer));

        return OrderMapper.mapToResponse(saved);
    }

    public void delete (Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", String.valueOf(id), "id"));
        orderRepository.delete(order);
    }
}
