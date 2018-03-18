package com.example.unit.resources.product;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.unit.template.VanHackTemplateLoader;
import com.vanhack.api.core.service.product.ProductServiceImpl;
import com.vanhack.api.resources.infraestructure.ApiExceptionHandler;
import com.vanhack.api.resources.products.ProductController;
import com.vanhack.api.resources.products.response.ProductResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private static final String PRODUCT_CONTROLLER_PATH = "/v1/products";

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    private static MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();

        FixtureFactoryLoader.loadTemplates("com.example.unit.template");
    }


    @Test
    public void queryAllProducts() throws Exception {

        ProductResponse productResponse = Fixture.from(ProductResponse.class).gimme(VanHackTemplateLoader.PRODUCT_TO_BE_LISTED);


        List<ProductResponse> productResponseList = new LinkedList<>();
        productResponseList.add(productResponse);

        when(productService.getProducts(null, null)).thenReturn(productResponseList);

        mockMvc.perform(get(PRODUCT_CONTROLLER_PATH))
                .andExpect(status().isOk());
    }
}
