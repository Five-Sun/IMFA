package com.fivesun.api.domain.auth.dto.response;

public record KakaoCallbackResponse(String accessToken, String refreshToke, boolean isNewUser) {}
