package com.example.mangodbcookiememorycache.domain.entity;


import lombok.*;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @DBRef
    private List<ToDoTask> tasks;
}
