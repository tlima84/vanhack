package com.vanhack.api.resources.order.request;

import com.vanhack.api.core.enums.OrderStatus;
import com.vanhack.api.resources.products.request.ProductRequest;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequest {

    private List<ProductRequest> productList;
    private OrderStatus status;
    private Double value;

}
