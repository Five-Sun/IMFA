package com.fivesun.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fivesun.common.response.ApiResponse;
import com.fivesun.common.response.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CommonException.class)
  public ApiResponse<Void> handleCommonException(CommonException e) {
    log.warn("[CommonException] {}", e.getMessage());
    return ApiResponse.error(e.getErrorCode());
  }

  @ExceptionHandler(Exception.class)
  public ApiResponse<Void> handleException(Exception e) {
    log.error("[Exception] {}", e.getMessage(), e);
    return ApiResponse.error(ErrorCode.INTERNAL_ERROR);
  }
}
