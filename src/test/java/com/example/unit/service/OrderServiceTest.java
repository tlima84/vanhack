package com.example.unit.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.unit.template.VanHackTemplateLoader;
import com.vanhack.api.core.repository.OrderRepository;
import com.vanhack.api.core.repository.model.Order;
import com.vanhack.api.core.repository.model.Product;
import com.vanhack.api.core.service.order.OrderServiceImpl;
import com.vanhack.api.core.service.product.ProductService;
import com.vanhack.api.resources.order.request.OrderRequest;
import com.vanhack.api.resources.order.response.OrderResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;


    @Before
    public void init() {
        FixtureFactoryLoader.loadTemplates("com.example.unit.template");
    }

    @Test
    public void createOrder(){

        when(productService.findProductBySlug("product-slug"))
                .thenReturn(Fixture.from(Product.class).gimme(VanHackTemplateLoader.PRODUCT_ENTITY));

        when(orderRepository.saveAndFlush(Fixture.
                from(Order.class).gimme(VanHackTemplateLoader.ORDER_ENTITY_REQUEST))).thenReturn(Fixture.
                from(Order.class).gimme(VanHackTemplateLoader.ORDER_ENTITY));

        OrderResponse response = orderService.
                createOrder(Fixture.from(OrderRequest.class)
                        .gimme(VanHackTemplateLoader.ORDER_REQUEST));

        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    public void cancelOrder(){

        String orderId = UUID.randomUUID().toString();

        Order order = Fixture.from(Order.class)
                .gimme(VanHackTemplateLoader.ORDER_ENTITY);

        when(orderRepository.findById(UUID.fromString(orderId)))
                .thenReturn(Optional.of(order));

        when(orderRepository.saveAndFlush(order)).thenReturn(Order.builder().build());

        orderService.cancelOrder(orderId);
    }

    @Test
    public void getOrderStatusTest(){
        String orderId = UUID.randomUUID().toString();

        Order order = Fixture.from(Order.class)
                .gimme(VanHackTemplateLoader.ORDER_ENTITY);

        when(orderRepository.findById(UUID.fromString(orderId)))
                .thenReturn(Optional.of(order));

        OrderResponse response = orderService.getOrder(orderId);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getOrderStatus());
    }
}
