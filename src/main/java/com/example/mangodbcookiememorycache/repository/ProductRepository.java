package com.example.mangodbcookiememorycache.repository;

import com.example.mangodbcookiememorycache.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
