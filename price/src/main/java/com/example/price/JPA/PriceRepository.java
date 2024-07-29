package com.example.price.JPA;

import com.example.price.Price.Price;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    Price findByUuid(String uuid);
}
