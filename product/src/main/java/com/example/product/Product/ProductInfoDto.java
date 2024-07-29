package com.example.product.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfoDto {
    protected String Id;

    private String name;

    private String description;

    private Object price;

}
