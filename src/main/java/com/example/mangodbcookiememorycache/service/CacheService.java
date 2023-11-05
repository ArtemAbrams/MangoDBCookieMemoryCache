package com.example.mangodbcookiememorycache.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CacheService<T> {
    void createCache(String cacheName, T element, String key);
    void deleteCache(String cacheName, String key);
    void updateCache(String cacheName, T element, String key);
    List<T> getAll(String cacheName);
}
