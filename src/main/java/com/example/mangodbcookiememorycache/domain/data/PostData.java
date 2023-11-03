package com.example.mangodbcookiememorycache.domain.data;

import com.example.mangodbcookiememorycache.domain.entity.Comment;
import com.example.mangodbcookiememorycache.domain.entity.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class PostData {
    private String title;
    private String content;
    private List<CommentData> comments;
}
