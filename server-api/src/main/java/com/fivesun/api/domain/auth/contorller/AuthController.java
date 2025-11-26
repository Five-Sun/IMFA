package com.fivesun.api.domain.auth.contorller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.request.RefreshRequest;
import com.fivesun.api.domain.auth.dto.response.TokenResponse;
import com.fivesun.api.domain.auth.service.AuthService;
import com.fivesun.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "인증 관련 컨트롤러")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/refresh")
  @Operation(summary = "토큰 재발급 요청")
  public ApiResponse<TokenResponse> refresh(@RequestBody RefreshRequest request) {
    TokenResponse response = authService.refreshTokens(request.refreshToken());
    return ApiResponse.ok(response);
  }
}
