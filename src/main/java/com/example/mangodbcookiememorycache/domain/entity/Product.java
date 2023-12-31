package com.example.mangodbcookiememorycache.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Builder
public class Product {
    @Id
    private String name;
    private double price;
    @DBRef
    private List<Country> countries;
}
