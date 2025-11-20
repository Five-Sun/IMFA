package com.fivesun.api.domain.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOAuthProperties(
    String clientId, String clientSecret, String redirectUri, String tokenUri, String authUri) {}
