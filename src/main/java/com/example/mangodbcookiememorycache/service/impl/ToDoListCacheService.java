package com.example.mangodbcookiememorycache.service.impl;

import com.example.mangodbcookiememorycache.domain.dto.ToDoTaskDTO;
import com.example.mangodbcookiememorycache.mapper.ToDoTaskMapper;
import com.example.mangodbcookiememorycache.repository.ToDoRepository;
import com.example.mangodbcookiememorycache.service.CacheService;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoListCacheService implements CacheService<ToDoTaskDTO> {
    private final CacheManager cacheManager;
    private final ToDoRepository toDoRepository;
    private final static String CACHE_NAME = "to_do_list";
    @Override
    public void createCache(String cacheName, ToDoTaskDTO element, String key) {
        var cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.put(key, element);
        }
    }

    @Override
    public void deleteCache(String cacheName, String key) {
        var cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.evictIfPresent(key);
        }
    }

    @Override
    public void updateCache(String cacheName, ToDoTaskDTO element, String key) {
        var cache = cacheManager.getCache(cacheName);
        if(cache!=null){
            cache.put(key, element);
        }
    }

    @Override
    public List<ToDoTaskDTO> getAll(String cacheName) {
        CaffeineCache cache = (CaffeineCache) cacheManager.getCache(cacheName);
        var tasks = new ArrayList<ToDoTaskDTO>();
        if (cache != null) {
            var nativeCache = (Cache<Object, Object>) cache.getNativeCache();
            Map<Object, Object> allEntries = nativeCache.asMap();
            if (allEntries.isEmpty() || allEntries
                    .values().size()!=toDoRepository.findAll().size())
                tasks = setIfCacheEmpty();
            else {
                for (Object entry : allEntries.values()) {
                    if (entry instanceof ToDoTaskDTO) {
                        tasks.add((ToDoTaskDTO) entry);
                    }
                }
            }
        }
        return tasks;
    }
    private ArrayList<ToDoTaskDTO> setIfCacheEmpty(){
         var tasks = toDoRepository.findAll()
                 .stream()
                 .map(ToDoTaskMapper::entityToDTO)
                 .collect(Collectors.toCollection(ArrayList::new));
         tasks.forEach(
                 e->createCache(CACHE_NAME, e, e.getId())
         );
         return tasks;
    }
}
