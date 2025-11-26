package com.fivesun.api.domain.auth.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.response.TokenResponse;
import com.fivesun.api.domain.auth.repository.RefreshTokenRepository;
import com.fivesun.api.security.JwtProvider;
import com.fivesun.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev")
@Tag(name = "Dev Controller", description = "개발 편의용 컨트롤러")
public class DevController {

  private final JwtProvider jwtProvider;
  private final RefreshTokenRepository refreshTokenRepository;

  @GetMapping("/token")
  @Operation(summary = "개발용 토큰 발급 요청")
  public ApiResponse<TokenResponse> devToken(@RequestParam Long userId) {
    String access = jwtProvider.createAccessToken(userId);
    String refresh = jwtProvider.createRefreshToken(userId);

    refreshTokenRepository.save(userId, refresh, 100000000);
    TokenResponse response = new TokenResponse(access, refresh);
    return ApiResponse.ok(response);
  }
}
