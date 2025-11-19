package com.fivesun.api.domain.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.jwt")
public record JwtProperties(String secret, long accessTokenValidity, long refreshTokenValidity) {}
