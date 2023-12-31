package com.example.mangodbcookiememorycache.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
@Builder
public class ToDoTask {
    @Id
    private String id;
    private String text;
    private LocalDateTime creationDateTime;

}
