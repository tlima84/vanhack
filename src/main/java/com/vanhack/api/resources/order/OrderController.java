package com.vanhack.api.resources.order;

import com.vanhack.api.core.service.order.OrderService;
import com.vanhack.api.resources.order.request.OrderRequest;
import com.vanhack.api.resources.order.response.OrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Api
@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

    private static final String ORDER_V1 = "/v1/orders";

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create an order",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful order creation", response = OrderResponse.class),
            @ApiResponse(code = 412, message = "Invalid data")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest, UriComponentsBuilder builder){

        OrderResponse response = orderService.createOrder(orderRequest);

        UriComponents uriComponents =
                builder.path(ORDER_V1.concat("/{slug}")).buildAndExpand(response.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @ApiOperation(value = "Cancel order",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order canceled with success", response = OrderResponse.class),
            @ApiResponse(code = 404, message = "Invalid order reference")})
    @RequestMapping(method = RequestMethod.PATCH, value = "{orderId}")
    public ResponseEntity cancelOrder(@PathVariable("orderId")String orderId){

        orderService.cancelOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get order status",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order returned with success", response = OrderResponse.class),
            @ApiResponse(code = 404, message = "Invalid order reference")})
    @RequestMapping(method = RequestMethod.GET, value = "{orderId}")
    public ResponseEntity getOrder(@PathVariable("orderId")String orderId){
        return ResponseEntity.ok().body(orderService.getOrder(orderId));
    }
}
