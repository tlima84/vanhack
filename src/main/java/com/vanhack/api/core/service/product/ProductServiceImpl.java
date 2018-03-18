package com.vanhack.api.core.service.product;

import com.vanhack.api.core.repository.ProductRepository;
import com.vanhack.api.core.repository.model.Product;
import com.vanhack.api.resources.products.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductResponse> getProducts(String slug, String name) {

        List<Product> productList = productRepository.
                findAll(Example.of(
                        Product.builder()
                                .slug(slug)
                                .name(name)
                                .build()
                ));

        return productList.stream().map(product -> ProductResponse
                .builder()
                .name(product.getName())
                .slug(product.getSlug())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }

    @Override
    public List<Product> findByList(List<String> productList) {
        return productRepository.findAll(productList);
    }


}
