package com.example.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import static jakarta.persistence.GenerationType.UUID;
@Data
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = UUID)
    protected String Id;

    private String name;

    private String description;

    public Product() {
    }

    public Product(String Id, String name, String description) {
        this.Id = Id;
        this.name = name;
        this.description = description;
    }
}
