package com.fivesun.api.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fivesun.api.domain.auth.config.KakaoOAuthProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoLoginUrlService {

  private final KakaoOAuthProperties kakaoProperties;

  public String generateLoginUrl() {
    return UriComponentsBuilder.fromHttpUrl(kakaoProperties.authUri())
        .queryParam("client_id", kakaoProperties.clientId())
        .queryParam("redirect_uri", kakaoProperties.redirectUri())
        .queryParam("response_type", "code")
        .queryParam("scope", "openid profile account_email")
        .toUriString();
  }
}
