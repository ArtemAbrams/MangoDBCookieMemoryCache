package com.example.mangodbcookiememorycache.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Data
@Builder
public class Comment {
    @Id
    private String id;

    private String text;

    @DBRef
    private Post post;

    @DBRef
    private User user;
}
