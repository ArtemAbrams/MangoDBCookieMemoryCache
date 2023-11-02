package com.example.mangodbcookiememorycache.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "posts")
@Data
public class Post {
    @Id
    private String id;

    private String title;
    private String content;

    @DBRef
    private User author;
    @DBRef
    private List<Comment> comments;
}
