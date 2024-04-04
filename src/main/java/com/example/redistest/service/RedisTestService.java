package com.example.redistest.service;

import com.example.redistest.cache.RedisCache;
import lombok.Builder;

@Builder
public class RedisTestService {
    private static RedisTestService INSTANCE;
    private RedisCache redisCache;
    public static RedisTestService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = RedisTestService.builder()
                    .redisCache(RedisCache.getInstance())
                    .build();
        }
        return INSTANCE;
    }
    public int getBonitaet(String id) {
        int bonitaet = (int) (Math.random() * 100) % 11;
        redisCache.write(id, bonitaet);
        redisCache.destroyConnection();
        return bonitaet;
    }
}