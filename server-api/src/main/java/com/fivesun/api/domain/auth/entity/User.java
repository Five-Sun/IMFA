package com.fivesun.api.domain.auth.entity;

import java.time.LocalDateTime;

import com.fivesun.api.domain.auth.constant.Provider;
import com.fivesun.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uq_provider_sub",
          columnNames = {"provider", "sub"})
    })
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Provider provider;

  @Column(nullable = false, length = 100)
  private String sub;

  private String email;

  private String nickname;

  private LocalDateTime lastLoginAt;

  @Builder
  private User(Provider provider, String sub, String email, String nickname) {
    this.provider = provider;
    this.sub = sub;
    this.email = email;
    this.nickname = nickname;
  }

  public void updateLoginTime() {
    this.lastLoginAt = LocalDateTime.now();
  }
}
