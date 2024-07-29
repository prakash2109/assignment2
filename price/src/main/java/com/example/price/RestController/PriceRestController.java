package com.example.price.RestController;

import com.example.price.JPA.PriceRepository;
import com.example.price.Price.Price;
import com.example.price.Price.PriceJSON;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/price")
public class PriceRestController {
    private final PriceRepository priceRepository;
    public PriceRestController (PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    @RequestMapping(path = "/getPrice/{uuid}", method = RequestMethod.GET)
    public Price getPrice(@PathVariable String uuid) {
        return priceRepository.findByUuid(uuid);
    }

}
