package com.fivesun.api.domain.auth.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fivesun.api.domain.auth.client.KakaoOAuthClient;
import com.fivesun.api.domain.auth.config.JwtProperties;
import com.fivesun.api.domain.auth.config.KakaoOAuthProperties;
import com.fivesun.api.domain.auth.dto.response.KakaoCallbackResponse;
import com.fivesun.api.domain.auth.dto.response.KakaoTokenResponse;
import com.fivesun.api.domain.auth.entity.User;
import com.fivesun.api.domain.auth.repository.RefreshTokenRepository;
import com.fivesun.api.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

  private final KakaoOAuthClient kakaoOAuthClient;
  private final JwtProvider jwtProvider;
  private final JwtDecoder jwtDecoder;
  private final UserService userService;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtProperties jwtProperties;
  private final KakaoOAuthProperties kakaoProperties;

  /**
   * 카카오 로그인 요청 URL 생성
   *
   * @return
   */
  public String generateLoginUrl() {
    return UriComponentsBuilder.fromHttpUrl(kakaoProperties.authUri())
        .queryParam("client_id", kakaoProperties.clientId())
        .queryParam("redirect_uri", kakaoProperties.redirectUri())
        .queryParam("response_type", "code")
        .queryParam("scope", "openid profile_nickname account_email")
        .toUriString();
  }

  /**
   * 카카오 OIDC 콜백 처리
   *
   * @param code
   * @return
   */
  public KakaoCallbackResponse handleCallback(String code) {

    // 1) 인가코드로 토큰 요청
    KakaoTokenResponse token = kakaoOAuthClient.fetchToken(code);
    String idToken = token.idToken();

    // 2) id_token OIDC 검증 (RSA/JWK 기반)
    Jwt jwt = jwtDecoder.decode(idToken);

    String sub = jwt.getClaim("sub");
    String email = jwt.getClaim("email");
    String nickname = jwt.getClaim("nickname");

    // 3) DB 저장/업서트
    User user = userService.upsertKakaoUser(sub, email, nickname);

    // 4) IMFA 자체 Access/RefreshToken 발급
    String access = jwtProvider.createAccessToken(user.getId());
    String refresh = jwtProvider.createRefreshToken(user.getId());
    boolean isNewUser = user.getCreatedAt().equals(user.getLastLoginAt());

    // 5) Redis RefreshToken 저장
    refreshTokenRepository.save(user.getId(), refresh, jwtProperties.refreshTokenValidity());

    return new KakaoCallbackResponse(access, refresh, isNewUser);
  }
}
