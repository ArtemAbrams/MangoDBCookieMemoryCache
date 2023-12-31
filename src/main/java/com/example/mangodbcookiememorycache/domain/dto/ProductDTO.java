package com.example.mangodbcookiememorycache.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private double price;
    List<CountryDTO> countryDTOS;

}
