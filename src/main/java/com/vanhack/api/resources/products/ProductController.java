package com.vanhack.api.resources.products;

import com.vanhack.api.core.service.product.ProductService;
import com.vanhack.api.resources.common.response.CollectionResponse;
import com.vanhack.api.resources.products.response.ProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {

    private static final String PRODUCT_V1 = "/v1/products";

    @Autowired
    private ProductService productService;

    @ApiOperation(value = " Fetch all products ", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return found produts", response = ProductResponse.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Invalid data")})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionResponse<ProductResponse>> getProducts(String slug, String name){


        return ResponseEntity.ok()
                .body(CollectionResponse.<ProductResponse>builder()
                        .result(productService.getProducts(slug, name)).build());
    }
}
