package com.fivesun.api.domain.auth.service;

import org.springframework.stereotype.Service;

import com.fivesun.api.domain.auth.dto.response.TokenResponse;
import com.fivesun.api.domain.auth.repository.RefreshTokenRepository;
import com.fivesun.api.security.JwtProvider;
import com.fivesun.common.exception.CommonException;
import com.fivesun.common.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtProvider jwtProvider;
  private final RefreshTokenRepository refreshTokenRepository;

  /**
   * 토큰 갱신
   *
   * @param refreshToken
   * @return
   */
  public TokenResponse refreshTokens(String refreshToken) {

    // 1) refresh token 검증 및 userId 파싱
    Long userId = jwtProvider.parseUserId(refreshToken);

    // 2) 저장된 refresh 비교
    String stored = refreshTokenRepository.get(userId);
    if (stored == null || !stored.equals(refreshToken)) {
      // 토큰 오류는 이후 예외 핸들러에서
      throw new CommonException(ErrorCode.INVALID_TOKEN);
    }

    // 3) 새 토큰 발급
    String newAccess = jwtProvider.createAccessToken(userId);
    String newRefresh = jwtProvider.createRefreshToken(userId);

    refreshTokenRepository.save(userId, newRefresh, jwtProvider.getRefreshTokenValidity());

    return new TokenResponse(newAccess, newRefresh);
  }
}
