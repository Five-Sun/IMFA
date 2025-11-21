package com.fivesun.api.domain.auth.contorller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fivesun.api.domain.auth.dto.response.UserResponse;
import com.fivesun.api.domain.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @GetMapping("/me")
  public UserResponse getMe(Authentication authentication) {
    return userService.selectMe(authentication);
  }
}
