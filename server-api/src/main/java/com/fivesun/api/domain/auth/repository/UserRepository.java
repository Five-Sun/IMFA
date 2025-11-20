package com.fivesun.api.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fivesun.api.domain.auth.constant.Provider;
import com.fivesun.api.domain.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByProviderAndSub(Provider provider, String sub);

  boolean existsByProviderAndSub(Provider provider, String sub);
}
