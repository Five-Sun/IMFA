package com.fivesun.api.domain.auth.contorller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.response.UserResponse;
import com.fivesun.api.domain.auth.service.UserService;
import com.fivesun.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "유저 관련 컨트롤러")
public class UserController {
  private final UserService userService;

  @GetMapping("/me")
  @Operation(summary = "유저 정보 조회 요청")
  public ApiResponse<UserResponse> getMe(Authentication authentication) {
    UserResponse response = userService.selectMe(authentication);
    return ApiResponse.ok(response);
  }

  @PostMapping("/logout")
  public ApiResponse<Void> logout(Authentication authentication) {
    userService.logout(authentication);
    return ApiResponse.ok();
  }
}
