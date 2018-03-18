package com.vanhack.api.core.service.product;

import com.vanhack.api.core.repository.model.Product;
import com.vanhack.api.resources.products.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts(String slug, String name);
    Product findProductBySlug(String slug);
    List<Product> findByList(List<String> productList);
}
