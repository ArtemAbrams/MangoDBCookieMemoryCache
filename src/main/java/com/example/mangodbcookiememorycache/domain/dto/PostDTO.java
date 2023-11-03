package com.example.mangodbcookiememorycache.domain.dto;

import com.example.mangodbcookiememorycache.domain.data.CommentData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostDTO {
    private String title;
    private String content;
    private List<CommentDTO> comments;
}
