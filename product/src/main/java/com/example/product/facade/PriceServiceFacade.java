package com.example.product.facade;

import org.springframework.stereotype.Service;

@Service
public interface PriceServiceFacade {
    PriceDto getPriceForProduct(String id);

    PriceDto insertPrice(PriceDto build);
}
