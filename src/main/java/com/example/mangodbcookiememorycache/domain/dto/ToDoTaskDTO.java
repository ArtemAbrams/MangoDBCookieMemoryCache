package com.example.mangodbcookiememorycache.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToDoTaskDTO {
    private String id;
    private String text;
    public LocalDateTime creationDateTime;
}
