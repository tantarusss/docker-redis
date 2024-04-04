package com.example.redistest.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
public class RedisCache {
    LettuceConnectionFactory connectionFactory;
    RedisTemplate<String, Integer> template;
    private static RedisCache INSTANCE;
    public static RedisCache getInstance() {
        if (INSTANCE == null) {
                INSTANCE = new RedisCache(new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379)), new RedisTemplate<>());
        }
        return INSTANCE;
    }
    private RedisCache(LettuceConnectionFactory connectionFactory, RedisTemplate<String, Integer> template) {
        this.connectionFactory = connectionFactory;
        this.template = template;
        setup();
    }
    public void setup() {
        connectionFactory.afterPropertiesSet();
        template.setConnectionFactory(connectionFactory);
        //template.setDefaultSerializer(StringRedisSerializer.UTF_8);
        template.afterPropertiesSet();
    }
    public void write(String key, Integer value) {
        template.opsForValue().set(key, value);
    }

    public Integer get(String key) {
        return template.opsForValue().get(key);
    }
    public void destroyConnection() {
        connectionFactory.destroy();
    }
}
