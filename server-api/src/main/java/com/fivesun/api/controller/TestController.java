package com.fivesun.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Test API", description = "테스트 용")
public class TestController {

  @GetMapping("/hello")
  @Operation(summary = "테스트", description = "테스트용입니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "404", description = "없음")
  })
  public String hello(
      @Parameter(description = "사용자 ID", example = "123") @RequestParam(required = false)
          String name) {
    return "Hello, This is API Test Page " + name;
  }
}
