package com.fivesun.api.config;

import com.fivesun.common.config.SwaggerConfig;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApiSwaggerConfig extends SwaggerConfig {

    @Bean
    public OpenAPI apiOpenAPI() {
        return setOpenAPI(
                "IMFA API Server",
                "이용기관(API 서버) 전용 문서",
                "1.0.0"
        );
    }
}
