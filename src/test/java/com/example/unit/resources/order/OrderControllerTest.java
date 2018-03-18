package com.example.unit.resources.order;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.unit.template.VanHackTemplateLoader;
import com.example.unit.util.ParserUtil;
import com.vanhack.api.core.service.order.OrderService;
import com.vanhack.api.resources.infraestructure.ApiExceptionHandler;
import com.vanhack.api.resources.order.OrderController;
import com.vanhack.api.resources.order.request.OrderRequest;
import com.vanhack.api.resources.order.response.OrderResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    private static final String ORDER_CONTROLLER_PATH = "/v1/orders";

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private static MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();

        FixtureFactoryLoader.loadTemplates("com.example.unit.template");
    }

    @Test
    public void createOrderTest() throws Exception {

        OrderRequest order = Fixture.from(OrderRequest.class).gimme(VanHackTemplateLoader.ORDER_REQUEST);

        when(orderService.createOrder(order)).thenReturn(Fixture.from(OrderResponse.class).gimme(VanHackTemplateLoader.ORDER_RESPONSE));

        mockMvc.perform(
                post(ORDER_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ParserUtil.asJsonString(order))
        ).andExpect(status().isCreated());

        verify(orderService, times(1)).createOrder(order);
    }

    @Test
    public void cancelOrderTest() throws Exception {

        String orderId = UUID.randomUUID().toString();

        doNothing().when(orderService).cancelOrder(orderId);

        mockMvc.perform(
                patch(ORDER_CONTROLLER_PATH + "/" +orderId)
        ).andExpect(status().isOk());

        verify(orderService, times(1)).cancelOrder(orderId);
    }

    @Test
    public void getOrderStatus() throws Exception {

        String orderId = UUID.randomUUID().toString();

        when(orderService.getOrder(orderId))
                .thenReturn(Fixture.from(OrderResponse.class)
                        .gimme(VanHackTemplateLoader.ORDER_RESPONSE));

        mockMvc.perform(
                get(ORDER_CONTROLLER_PATH + "/" +orderId)
        ).andExpect(status().isOk());
    }
}
