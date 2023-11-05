package com.example.mangodbcookiememorycache.configuration;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfiguration {
    @Value(value = "${cache.expiration}")
    private long expirationTime;
    @Value(value = "${cache.names}")
    private String cacheNames;
    @Bean
    public CacheManager cacheManager(){
       var names = cacheNames.split(",");
       var cacheManager =  new CaffeineCacheManager(names);
       cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(expirationTime,
               TimeUnit.SECONDS));
       return cacheManager;
    }
}
