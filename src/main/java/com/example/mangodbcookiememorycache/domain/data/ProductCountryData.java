package com.example.mangodbcookiememorycache.domain.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCountryData {
    private String name;
    private double price;
    private List<CountryData> countries;
}
