package com.example.mangodbcookiememorycache.repository;

import com.example.mangodbcookiememorycache.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Long> {
}
