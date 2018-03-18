package com.vanhack.api.core.service.order;

import com.vanhack.api.core.enums.OrderStatus;
import com.vanhack.api.core.exception.EntityNotFoundException;
import com.vanhack.api.core.repository.OrderRepository;
import com.vanhack.api.core.repository.model.Order;
import com.vanhack.api.core.service.product.ProductService;
import com.vanhack.api.resources.order.request.OrderRequest;
import com.vanhack.api.resources.order.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Order orderTobeSaved = Order.builder()
                .productList(orderRequest.getProductList().stream()
                        .map(productRequest -> productService
                                .findProductBySlug(productRequest.getSlug())).collect(Collectors.toList()))
                .status(orderRequest.getStatus().getStatus()).value(orderRequest.getValue()).build();

        Order order = orderRepository.saveAndFlush(orderTobeSaved);

        return OrderResponse.builder().id(order.getId()).build();
    }

    @Override
    public void cancelOrder(String orderId) {

        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Order not found for id='%s'", orderId)));

        orderRepository.saveAndFlush(Order.builder()
                .value(order.getValue())
                .status(OrderStatus.CANCELED.getStatus())
                .productList(order.getProductList()).id(order.getId()).build());
    }

    @Override
    public OrderResponse getOrder(String orderId) {

        Order order = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Order not found for id='%s'", orderId)));

        return OrderResponse.builder().id(order.getId())
                .orderStatus(OrderStatus.getByStatus(order.getStatus()))
                .build();
    }
}
