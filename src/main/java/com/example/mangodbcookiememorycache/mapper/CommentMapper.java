package com.example.mangodbcookiememorycache.mapper;

import com.example.mangodbcookiememorycache.domain.data.CommentData;
import com.example.mangodbcookiememorycache.domain.dto.CommentDTO;
import com.example.mangodbcookiememorycache.domain.entity.Comment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommentMapper {
    public Comment dataToEntity(CommentData commentData){
        return Comment.builder()
                .text(commentData.getText())
                .build();
    }
    public CommentDTO entityToDTO(Comment comment){
        return CommentDTO
                .builder()
                .text(comment.getText())
                .build();
    }
}
