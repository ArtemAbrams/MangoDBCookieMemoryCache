package com.example.mangodbcookiememorycache.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Country {
    @Id
    private String name;
    @DBRef
    private Product product;
    public Country(String name){
        this.name=name;
    }
}
