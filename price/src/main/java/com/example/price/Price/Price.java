package com.example.price.Price;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = SEQUENCE,  generator = "price_seq_gen")
    @SequenceGenerator(name = "price_seq_gen", sequenceName = "price_SEQ", allocationSize = 1)
    protected int id;

    private String uuid;

    @Convert(converter = PriceJOSNConverter.class)
    @Column(name = "price",columnDefinition = "jsonb")
    private PriceJSON price;

}
