package com.example.unit.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.unit.template.VanHackTemplateLoader;
import com.vanhack.api.core.repository.ProductRepository;
import com.vanhack.api.core.repository.model.Product;
import com.vanhack.api.core.service.product.ProductServiceImpl;
import com.vanhack.api.resources.products.response.ProductResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void init() {
        FixtureFactoryLoader.loadTemplates("com.example.unit.template");
    }


    @Test
    public void allProductsQueryTest(){

        Product product = Fixture.from(Product.class)
                .gimme(VanHackTemplateLoader.PRODUCT_ENTITY);

        List<Product> productList = new LinkedList<>();
        productList.add(product);

        Product emptyProduct = Product.builder().build();

        when(productRepository.findAll(Example.of(emptyProduct))).thenReturn(productList);

        List<ProductResponse> productResponseList = productService.getProducts(null, null);
        assertNotNull(productResponseList);
        assertFalse(productResponseList.isEmpty());
        assertNotNull(productResponseList.get(0).getName());

    }
}
