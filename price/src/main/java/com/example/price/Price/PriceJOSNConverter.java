package com.example.price.Price;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class PriceJOSNConverter implements AttributeConverter<PriceJSON, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(PriceJSON priceJSON) {
        try {
            return objectMapper.writeValueAsString(priceJSON);
        } catch (JsonProcessingException jpe) {
            return null;
        }
    }

    @Override
    public PriceJSON convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, PriceJSON.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
