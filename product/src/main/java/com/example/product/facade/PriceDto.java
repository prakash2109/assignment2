package com.example.product.facade;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class PriceDto {

    protected String id;
    private String uuid;
    private Object price;
}
