package com.fivesun.api.domain.auth.repository;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

  private final String PREFIX = "RT:";
  private final RedisTemplate<String, String> redisTemplate;

  // RefreshToken 저장
  public void save(Long userId, String refreshToken, long ttlMillis) {
    String key = PREFIX + userId;
    redisTemplate.opsForValue().set(key, refreshToken, Duration.ofMillis(ttlMillis));
  }

  // RefreshToken 조회
  public String get(Long userId) {
    return redisTemplate.opsForValue().get(PREFIX + userId);
  }

  // RefreshToken 삭제(로그 아웃)
  public void delete(Long userId) {
    redisTemplate.delete(PREFIX + userId);
  }
}
