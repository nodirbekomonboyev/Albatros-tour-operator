package com.nodirverse.albatros.service.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 5;

    private final RedisTemplate<String, Object> redisTemplate;

    public void loginFailed(String ip) {
        String cacheKey = getCacheKey(ip);
        String blockKey = getBlockKey(ip);
        int attempts = 0;
        if (redisTemplate.hasKey(cacheKey)) {
            attempts = (int) redisTemplate.opsForValue().get(cacheKey);
        }
        attempts++;
        if(attempts >= MAX_ATTEMPTS) {
            redisTemplate.delete(cacheKey);
            redisTemplate.opsForValue().set(blockKey, LocalDateTime.now().plusMinutes(2));
        } else {
            redisTemplate.opsForValue().set(cacheKey,attempts);
        }
    }


    public boolean isBlocked(String ip) {
        String blockKey = getBlockKey(ip);
        if(redisTemplate.hasKey(blockKey)) {
            if (LocalDateTime.now().isBefore((ChronoLocalDateTime<?>) redisTemplate.opsForValue().get(blockKey))) {
                return true;
            } else{
                redisTemplate.delete(blockKey);
            }

        }
        return false;
    }

    private String getCacheKey(String ip) {
        return "LOGIN_ATTEMPTS_IP_" + ip;
    }

    private String getBlockKey(String ip) {
        return "LOGIN_BLOCKED_IP_" + ip;
    }
}



