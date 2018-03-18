package com.vanhack.api.resources.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

import java.util.List;

@Getter
@Builder
public class CollectionResponse<T> {

    List<T> result;

    @Tolerate
    public CollectionResponse() {
        //default constructor
    }
}
