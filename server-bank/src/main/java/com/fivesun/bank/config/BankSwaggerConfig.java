package com.fivesun.bank.config;

import org.springframework.context.annotation.Bean;

import com.fivesun.common.config.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;

public class BankSwaggerConfig extends SwaggerConfig {

  @Bean
  public OpenAPI apiOpenAPI() {
    return setOpenAPI("IMFA Bank Server", "은행기관(Bank 서버) 전용 문서", "1.0.0");
  }
}
