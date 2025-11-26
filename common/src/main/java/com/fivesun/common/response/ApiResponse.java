package com.fivesun.common.response;

public record ApiResponse<T>(boolean success, String code, String message, T data) {
  // 성공 응답 (data 포함)
  public static <T> ApiResponse<T> ok(T data) {
    return new ApiResponse<>(true, "SUCCESS", "요청이 성공했습니다.", data);
  }

  // 성공 응답 (data 없음)
  public static ApiResponse<Void> ok() {
    return new ApiResponse<>(true, "SUCCESS", "요청이 성공했습니다.", null);
  }

  // 실패 응답 (ErrorCode 기반)
  public static ApiResponse<Void> error(ErrorCode code) {
    return new ApiResponse<>(false, code.getCode(), code.getMessage(), null);
  }

  // 실패 응답 (커스텀 메시지)
  public static ApiResponse<Void> error(String code, String message) {
    return new ApiResponse<>(false, code, message, null);
  }
}
