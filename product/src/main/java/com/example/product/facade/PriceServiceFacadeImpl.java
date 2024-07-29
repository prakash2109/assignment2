package com.example.product.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PriceServiceFacadeImpl implements PriceServiceFacade{
    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String priceServiceUrl;

    public PriceServiceFacadeImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public PriceDto getPriceForProduct(String uuid) {

        String url = String.format("%s/v1/price/getPrice/%s", priceServiceUrl, uuid);
        ResponseEntity<PriceDto> response = restTemplate.getForEntity(url, PriceDto.class);
        return response.getBody();

    }

    @Override
    public PriceDto insertPrice(PriceDto p) {
        String url = String.format("%s/v1/price/createPrice", priceServiceUrl);
        ResponseEntity<PriceDto> response = restTemplate.postForEntity(url, p, PriceDto.class);
        return response.getBody();
    }
}
