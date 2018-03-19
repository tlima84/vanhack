package com.example.unit.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.vanhack.api.core.enums.OrderStatus;
import com.vanhack.api.core.repository.model.Order;
import com.vanhack.api.core.repository.model.Product;
import com.vanhack.api.core.repository.model.User;
import com.vanhack.api.resources.order.request.OrderRequest;
import com.vanhack.api.resources.order.response.OrderResponse;
import com.vanhack.api.resources.products.request.ProductRequest;
import com.vanhack.api.resources.products.response.ProductResponse;

import java.util.UUID;

public class VanHackTemplateLoader implements TemplateLoader {

    private static final String USER = "user-entity-no-id";
    private static final String USER_WITH_ID = "user-entity";
    public static final String PRODUCT_TO_BE_LISTED = "product-to-be-listed";
    public static final String PRODUCT_ENTITY = "product-entity";
    public static final String ORDER_REQUEST = "order-request";
    private static final String PRODUCT_REQUEST = "product-request";
    public static final String ORDER_RESPONSE = "order-response";
    public static final String ORDER_ENTITY = "order-entity";
    public static final String ORDER_ENTITY_REQUEST = "order-entity-request";

    @Override
    public void load() {

        Fixture.of(User.class).addTemplate(USER, new Rule() {{
            add("slug", "user-slug");
            add("name", "user-name");
        }});

        Fixture.of(User.class).addTemplate(USER_WITH_ID, new Rule() {{
            add("objRef", "i12i3jo1ij23");
            add("slug", "user-slug");
            add("name", "user-name");
        }});

        Fixture.of(ProductResponse.class).addTemplate(PRODUCT_TO_BE_LISTED, new Rule() {{
            add("slug", "user-slug");
            add("name", "user-name");
            add("price", 10D);
        }});

        Fixture.of(Product.class).addTemplate(PRODUCT_ENTITY, new Rule() {{
            add("slug", "product-slug");
            add("name", "product-name");
            add("value", 10D);
        }});

        Fixture.of(ProductRequest.class).addTemplate(PRODUCT_REQUEST, new Rule() {{
            add("slug", "product-slug");
            add("name", "product-name");
            add("value", 10D);
        }});

        Fixture.of(OrderRequest.class).addTemplate(ORDER_REQUEST, new Rule() {{
            add("status", OrderStatus.PENDING);
            add("productList", has(2).of(ProductRequest.class, PRODUCT_REQUEST));
        }});

        Fixture.of(OrderResponse.class).addTemplate(ORDER_RESPONSE, new Rule() {{
            add("id", UUID.randomUUID());
        }});

        Fixture.of(Order.class).addTemplate(ORDER_ENTITY, new Rule() {{
            add("id", UUID.randomUUID());
            add("status", "pending");
        }});

        Fixture.of(Order.class).addTemplate(ORDER_ENTITY_REQUEST, new Rule() {{
            add("productList",has(2).of(Product.class,PRODUCT_ENTITY));
            add("status", "pending");
        }});

        Fixture.of(Order.class).addTemplate(ORDER_ENTITY, new Rule() {{
            add("id", UUID.randomUUID());
            add("status", "canceled");
        }});

    }
}
