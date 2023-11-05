package com.example.mangodbcookiememorycache.mapper;

import com.example.mangodbcookiememorycache.domain.data.ProductData;
import com.example.mangodbcookiememorycache.domain.data.UserData;
import com.example.mangodbcookiememorycache.domain.dto.ProductDTO;
import com.example.mangodbcookiememorycache.domain.dto.UserDTO;
import com.example.mangodbcookiememorycache.domain.entity.Product;
import com.example.mangodbcookiememorycache.domain.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {
    public Product dataToEntity(ProductData productData){
        return Product.builder()
                .name(productData.getName())
                .price(productData.getPrice())
                .build();
    }
    public ProductDTO entityToDTO(Product product){
        return ProductDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
