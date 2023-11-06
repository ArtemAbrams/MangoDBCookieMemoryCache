package com.example.mangodbcookiememorycache.controller.RestController;

import com.example.mangodbcookiememorycache.domain.entity.Product;
import com.example.mangodbcookiememorycache.repository.ProductRepository;
import com.example.mangodbcookiememorycache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/query")
@RequiredArgsConstructor
public class MongoDbQueryController {
    private final ProductRepository productRepository;
    @GetMapping("/products/max-price")
    public ResponseEntity<Double> getMaxProductPrice() {
        Double maxPrice = productRepository.topProductPrice();
        return ResponseEntity.ok(maxPrice);
    }

    @GetMapping("/products/search")
    public ResponseEntity<Product> getProductByNameAndPrice(@RequestParam String name, @RequestParam double price) {
        var product = productRepository.findByNameAndPriceMoreThenQuery(name, price);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/products/average-price")
    public ResponseEntity<Double> getAveragePrice() {
        double avgPrice = productRepository.averagePrice();
        return ResponseEntity.ok(avgPrice);
    }


    @GetMapping("/products/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        var products = productRepository.findAllProductsQuery();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/products/match-price")
    public ResponseEntity<List<Double>> getMatchAndProject(@RequestParam String pattern) {
        var prices = productRepository.matchAndProject(pattern);
        return ResponseEntity.ok(prices);
    }
    @GetMapping("/products/count")
    public ResponseEntity<Double> getCountOfProductsByNameLike(@RequestParam String name) {
        Double count = productRepository.countProductByNameLike(name);
        if(count != null) {
            return ResponseEntity.ok(count);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
