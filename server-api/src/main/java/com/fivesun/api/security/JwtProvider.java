package com.fivesun.api.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fivesun.api.domain.auth.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

  private final JwtProperties jwtProperties;

  public String createAccessToken(Long userId) {
    return Jwts.builder()
        .subject(String.valueOf(userId))
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtProperties.accessTokenValidity()))
        .signWith(Keys.hmacShaKeyFor(jwtProperties.secret().getBytes()))
        .compact();
  }

  public String createRefreshToken(Long userId) {
    return Jwts.builder()
        .subject(String.valueOf(userId))
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtProperties.refreshTokenValidity()))
        .signWith(Keys.hmacShaKeyFor(jwtProperties.secret().getBytes()))
        .compact();
  }

  public Long validateAndGetUserId(String token) {
    try {
      Claims claims =
          Jwts.parser()
              .verifyWith(Keys.hmacShaKeyFor(jwtProperties.secret().getBytes()))
              .build()
              .parseSignedClaims(token)
              .getPayload();

      return Long.valueOf(claims.getSubject());
    } catch (Exception e) {
      return null;
    }
  }
}
