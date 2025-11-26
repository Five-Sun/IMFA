package com.fivesun.api.domain.auth.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.response.KakaoCallbackResponse;
import com.fivesun.api.domain.auth.dto.response.KakaoLoginUrlResponse;
import com.fivesun.api.domain.auth.service.KakaoAuthService;
import com.fivesun.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/kakao")
@Tag(name = "Kakao Auth Controller", description = "카카오 소셜 로그인 관련 컨트롤러")
public class KakaoAuthController {
  private final KakaoAuthService kakaoAuthService;

  @GetMapping("/login-url")
  @Operation(summary = "Kakao 로그인 URL 요청")
  public ApiResponse<KakaoLoginUrlResponse> getLoginUrl() {
    KakaoLoginUrlResponse response = kakaoAuthService.generateLoginUrl();
    return ApiResponse.ok(response);
  }

  @GetMapping("/callback")
  @Operation(summary = "Kakao 로그인 callback 요청")
  public ApiResponse<KakaoCallbackResponse> kakaoCallback(@RequestParam("code") String code) {
    KakaoCallbackResponse response = kakaoAuthService.handleCallback(code);
    return ApiResponse.ok(response);
  }
}
