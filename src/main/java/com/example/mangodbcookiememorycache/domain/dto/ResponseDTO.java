package com.example.mangodbcookiememorycache.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseDTO<T> {
    private HttpStatus status;
    private T data;
}
