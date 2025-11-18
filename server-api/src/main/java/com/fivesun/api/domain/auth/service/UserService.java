package com.fivesun.api.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fivesun.api.domain.auth.constant.Provider;
import com.fivesun.api.domain.auth.entity.User;
import com.fivesun.api.domain.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

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
            () ->
                userRepository.save(
                    User.builder()
                        .provider(Provider.KAKAO)
                        .sub(sub)
                        .email(email)
                        .nickname(nickname)
                        .build()));
  }
}
