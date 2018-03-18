package com.vanhack.api.resources.products.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductRequest {

    private String slug;
    private String name;

    //TODO change to bigdecimal to next step of mvp!
    private Double value;


}
