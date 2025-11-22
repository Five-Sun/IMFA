package com.fivesun.api.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String header = request.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = header.substring(7);
    try {
      Long userId = jwtProvider.parseUserId(token);

      // 인증 객체 생성 후 SecurityContext 저장
      JwtUserPrincipal principal = new JwtUserPrincipal(userId);
      var auth =
          new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (Exception e) {
      // 토큰 오류는 이후 예외 핸들러에서
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);
  }
}
