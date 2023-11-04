package com.example.mangodbcookiememorycache.repository;

import com.example.mangodbcookiememorycache.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
