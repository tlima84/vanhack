package com.vanhack.api.core.service.order;

import com.vanhack.api.resources.order.request.OrderRequest;
import com.vanhack.api.resources.order.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest order);

    void cancelOrder(String orderId);

    OrderResponse getOrder(String orderId);
}
