package com.srividhya.hulkstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private Long productId;
    private String orderSuccessMessage;
    private String productName;
    private Integer availableProductCount;

}
