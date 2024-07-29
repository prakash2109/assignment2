package com.example.product.RestController;

import com.example.product.JPA.ProductRepository;
import com.example.product.Product.Product;
import com.example.product.Product.ProductInfoDto;
import com.example.product.facade.PriceDto;
import com.example.product.facade.PriceServiceFacade;
import com.example.product.Exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final ProductRepository productRepository;
    private final PriceServiceFacade priceServiceFacade;

    public ProductRestController(ProductRepository productRepository, PriceServiceFacade priceServiceFacade) {
        this.productRepository = productRepository;
        this.priceServiceFacade = priceServiceFacade;
    }

    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            if (product.getName() == null || product.getName().isEmpty()) {
                throw new ProductValidationException("Product name must not be empty");
            }
            if (product.getDescription() == null || product.getDescription().isEmpty()) {
                throw new ProductValidationException("Product description must not be empty");
            }
            Product savedProduct = productRepository.save(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (ProductValidationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ProductCreationException("Failed to create product", e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getProduct/{uuid}")
    public ResponseEntity<ProductInfoDto> getProductById(@PathVariable String uuid) {
        // Fetch the product from the repository
        Product product = productRepository.findById(uuid)
                .orElseThrow(() -> {
                    logger.warn("Product with UUID {} not found", uuid);
                    return new ProductNotFoundException("Product with UUID " + uuid + " not found");
                });

        // Fetch the price from the facade
        PriceDto price;
        try {
            price = priceServiceFacade.getPriceForProduct(uuid);
        } catch (Exception e) {
            logger.error("Error fetching price for product UUID {}: {}", uuid, e.getMessage());
            throw new PriceServiceException("Error fetching price for product UUID " + uuid, e);
        }

        // Build the ProductInfoDto
        ProductInfoDto productInfoDto = ProductInfoDto.builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(price.getPrice())
                .build();

        logger.info("Response: {}", productInfoDto);

        return new ResponseEntity<>(productInfoDto, HttpStatus.OK);
    }
}
