package com.fivesun.api.domain.auth.contorller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.request.RefreshRequest;
import com.fivesun.api.domain.auth.dto.response.TokenResponse;
import com.fivesun.api.domain.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/refresh")
  public TokenResponse refresh(@RequestBody RefreshRequest request) {
    return authService.refreshTokens(request.refreshToken());
  }
}
