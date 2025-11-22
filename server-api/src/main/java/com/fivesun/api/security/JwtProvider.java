package com.fivesun.api.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fivesun.api.domain.auth.config.JwtProperties;

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

  /** refreshToken 에서 userId(sub) 추출 */
  public Long parseUserId(String token) {
    var parsed =
        Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtProperties.secret().getBytes()))
            .build()
            .parseSignedClaims(token);

    return Long.valueOf(parsed.getPayload().getSubject());
  }

  /** refreshToken 유효시간 가져오기 */
  public long getRefreshTokenValidity() {
    return jwtProperties.refreshTokenValidity();
  }
}
