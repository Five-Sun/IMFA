package com.fivesun.api.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserPrincipal implements UserDetails {

  private final Long userId;

  public JwtUserPrincipal(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  @Override
  public List<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "ROLE_USER");
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return String.valueOf(userId);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
