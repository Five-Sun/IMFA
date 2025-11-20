package com.fivesun.api.domain.auth.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import com.fivesun.api.domain.auth.config.KakaoOAuthProperties;
import com.fivesun.api.domain.auth.dto.response.KakaoTokenResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient {
  private final RestClient restClient;
  private final KakaoOAuthProperties properties;

  public KakaoTokenResponse fetchToken(String code) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("grant_type", "authorization_code");
    formData.add("client_id", properties.clientId());
    formData.add("redirect_uri", properties.redirectUri());
    formData.add("code", code);

    if (properties.clientSecret() != null && !properties.clientSecret().isBlank()) {
      formData.add("client_secret", properties.clientSecret());
    }

    return restClient
        .post()
        .uri(properties.tokenUri())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(formData)
        .retrieve()
        .body(KakaoTokenResponse.class);
  }
}
