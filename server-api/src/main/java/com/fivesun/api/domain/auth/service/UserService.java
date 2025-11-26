package com.fivesun.api.domain.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fivesun.api.domain.auth.constant.Provider;
import com.fivesun.api.domain.auth.dto.response.UserResponse;
import com.fivesun.api.domain.auth.entity.User;
import com.fivesun.api.domain.auth.repository.RefreshTokenRepository;
import com.fivesun.api.domain.auth.repository.UserRepository;
import com.fivesun.api.security.JwtUserPrincipal;
import com.fivesun.common.exception.CommonException;
import com.fivesun.common.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  /**
   * 카카오 회원가입 및 로그인
   *
   * @param sub
   * @param email
   * @param nickname
   * @return
   */
  @Transactional
  public User upsertKakaoUser(String sub, String email, String nickname) {
    return userRepository
        .findByProviderAndSub(Provider.KAKAO, sub)
        .map(
            user -> {
              user.updateLoginTime();
              return user;
            })
        .orElseGet(
            () -> {
              User newUser = createUser(sub, email, nickname, Provider.KAKAO);
              newUser.updateLoginTime();
              return newUser;
            });
  }

  /**
   * 회원 가입
   *
   * @param sub
   * @param email
   * @param nickname
   * @param provider
   * @return
   */
  @Transactional
  public User createUser(String sub, String email, String nickname, Provider provider) {
    return userRepository.save(
        User.builder().provider(provider).sub(sub).email(email).nickname(nickname).build());
  }

  /**
   * 유저 정보 조회
   *
   * @param authentication
   * @return
   */
  public UserResponse selectMe(Authentication authentication) {
    JwtUserPrincipal principal = (JwtUserPrincipal) authentication.getPrincipal();
    Long userId = principal.getUserId();

    User user =
        userRepository.findById(userId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));

    return new UserResponse(user.getId(), user.getEmail(), user.getNickname());
  }

  public void logout(Authentication authentication) {
    Long userId = ((JwtUserPrincipal) authentication.getPrincipal()).getUserId();
    refreshTokenRepository.delete(userId);
  }
}
