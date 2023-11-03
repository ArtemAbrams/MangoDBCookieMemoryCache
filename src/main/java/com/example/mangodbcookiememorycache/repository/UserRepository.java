package com.example.mangodbcookiememorycache.repository;

import com.example.mangodbcookiememorycache.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
