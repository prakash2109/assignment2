package com.example.product.Exception;

public class PriceServiceException extends RuntimeException {
    public PriceServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
