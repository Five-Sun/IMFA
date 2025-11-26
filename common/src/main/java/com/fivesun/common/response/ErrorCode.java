package com.fivesun.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // 공통 에러
  BAD_REQUEST("4000", "잘못된 요청입니다."),
  UNAUTHORIZED("4010", "인증되지 않은 요청입니다."),
  FORBIDDEN("4030", "접근 권한이 없습니다."),
  NOT_FOUND("4040", "요청한 리소스를 찾을 수 없습니다."),
  INTERNAL_ERROR("5000", "서버 내부 오류입니다."),

  // 인증/인가 관련
  INVALID_TOKEN("4011", "유효하지 않은 토큰입니다."),
  EXPIRED_TOKEN("4012", "만료된 토큰입니다."),
  TOKEN_NOT_FOUND("4013", "토큰이 제공되지 않았습니다.");

  private final String code;
  private final String message;
}
