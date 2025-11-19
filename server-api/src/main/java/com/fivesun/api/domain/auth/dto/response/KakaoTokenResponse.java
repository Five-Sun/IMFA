package com.fivesun.api.domain.auth.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

// 카카오가 snake_case 로 값을 보내므로 Jackson 전략 적용
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record KakaoTokenResponse(
    String tokenType,
    String accessToken,
    String idToken,
    String refreshToken,
    Long expiresIn,
    Long refreshTokenExpiresIn) {}
