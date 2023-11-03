package com.example.mangodbcookiememorycache.domain.data;

import com.example.mangodbcookiememorycache.domain.entity.Post;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class UserData {
    private String email;
    private String firstName;
    private String lastName;
    private List<PostData> posts;
}
