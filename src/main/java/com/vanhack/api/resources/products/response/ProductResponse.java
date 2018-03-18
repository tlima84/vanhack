package com.vanhack.api.resources.products.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String slug;
    private String name;
    //TODO change to bigdecimal to next step of mvp!
    private Double price;


}
