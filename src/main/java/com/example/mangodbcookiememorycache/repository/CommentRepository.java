package com.example.mangodbcookiememorycache.repository;

import com.example.mangodbcookiememorycache.domain.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, Long> {

}
