package com.example.mangodbcookiememorycache.controller.RestController;

import com.example.mangodbcookiememorycache.domain.data.ProductData;
import com.example.mangodbcookiememorycache.mapper.ProductMapper;
import com.example.mangodbcookiememorycache.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final static String CACHE_NAME = "product";
    @PostMapping("/create")
    @Cacheable(cacheNames = {CACHE_NAME}, key = "#productData.name")
    public ResponseEntity<?> create(@RequestBody ProductData productData){
        try {
            var product = ProductMapper.dataToEntity(productData);
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductMapper.entityToDTO(product));
        }
        catch (Exception exception){
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
    @DeleteMapping("/delete/{name}")
    @CacheEvict(cacheNames = {CACHE_NAME}, key = "#name")
    public ResponseEntity<?> delete(@PathVariable String name) {
        try {
            productRepository.deleteById(name);
            return ResponseEntity
                    .ok()
                    .build();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
    @GetMapping("/getAll")
    @Cacheable(cacheNames = {CACHE_NAME})
    public ResponseEntity<?> getAll() {
        try {
          var products = productRepository.findAll()
                  .stream()
                  .map(ProductMapper::entityToDTO)
                  .toList();
          return ResponseEntity.status(HttpStatus.OK)
                  .body(products);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
    @GetMapping("/{name}")
    @Cacheable(cacheNames = {CACHE_NAME}, key = "#name")
    public ResponseEntity<?> getById(@PathVariable String name) {
        try {
            var product = productRepository.findById(name)
                    .orElseThrow(()-> new RuntimeException("Was not found"));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ProductMapper.entityToDTO(product));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
    @PutMapping("/update/{name}")
    @CachePut(cacheNames = {CACHE_NAME}, key = "#name")
    @Transactional
    public ResponseEntity<?> update(@PathVariable String name, @RequestBody ProductData productData) {
        try {
            var product = productRepository.findById(name)
                    .orElseThrow(() -> new RuntimeException("Product with name "+ name + " was not found"));
            product.setName(product.getName());
            product.setPrice(product.getPrice());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductMapper
                            .entityToDTO(product));
        }
        catch (Exception exception){
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
}
