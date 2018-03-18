package com.vanhack.api.core.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PENDING("pending"),
    DELIVERY("on-delivery"),
    CANCELED("canceled");

    private String status;

    OrderStatus(String status) {
        this.status  = status;
    }

    public static OrderStatus getByStatus(String status){
        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.status.equals(status)){
                return orderStatus;
            }
            continue;
        }
        return null;
    }
}
