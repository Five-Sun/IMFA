package com.fivesun.api.domain.auth.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.response.KakaoCallbackResponse;
import com.fivesun.api.domain.auth.dto.response.KakaoLoginUrlResponse;
import com.fivesun.api.domain.auth.service.KakaoAuthService;
import com.fivesun.api.domain.auth.service.KakaoLoginUrlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/kakao")
@Tag(name = "Kakao Auth Controller", description = "카카오 로그인")
public class KakaoAuthController {
  private final KakaoLoginUrlService kakaoLoginUrlService;
  private final KakaoAuthService kakaoAuthService;

  @GetMapping("/login-url")
  @Operation(summary = "Kakao 로그인 URL 요청")
  public KakaoLoginUrlResponse getLoginUrl() {
    String loginUrl = kakaoLoginUrlService.generateLoginUrl();
    return new KakaoLoginUrlResponse(loginUrl);
  }

  @GetMapping("/callback")
  @Operation(summary = "Kakao 로그인 callback 요청")
  public KakaoCallbackResponse kakaoCallback(@RequestParam("code") String code) {
    return kakaoAuthService.handleCallback(code);
  }
}
