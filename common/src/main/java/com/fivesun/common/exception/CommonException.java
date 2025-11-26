package com.fivesun.common.exception;

import com.fivesun.common.response.ErrorCode;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
  private final ErrorCode errorCode;

  public CommonException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
