package com.example.mangodbcookiememorycache.mapper;

import com.example.mangodbcookiememorycache.domain.data.PostData;
import com.example.mangodbcookiememorycache.domain.dto.PostDTO;
import com.example.mangodbcookiememorycache.domain.entity.Post;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostMapper {
    public Post dataToEntity(PostData postData){
        return Post.builder()
                .title(postData.getTitle())
                .content(postData.getContent())
                .comments(postData.getComments()
                        .stream()
                        .map(CommentMapper::dataToEntity)
                        .toList())
                .build();
    }
    public PostDTO entityToDTO(Post post){
        return PostDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .comments(post.getComments() != null ? post.getComments()
                        .stream()
                        .map(CommentMapper::entityToDTO)
                        .toList() : null)
                .build();
    }
}
