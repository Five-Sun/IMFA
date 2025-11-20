package com.fivesun.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fivesun.common.config.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class AuthSwaggerConfig extends SwaggerConfig {

  @Bean
  public OpenAPI apiOpenAPI() {
    return setOpenAPI("IMFA Auth Server", "인증기관(Auth 서버) 전용 문서", "1.0.0");
  }
}
