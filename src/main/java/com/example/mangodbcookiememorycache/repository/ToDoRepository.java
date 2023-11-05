package com.example.mangodbcookiememorycache.repository;


import com.example.mangodbcookiememorycache.domain.entity.ToDoTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDoTask, String> {
}
