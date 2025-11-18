package com.fivesun.common.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

/*    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IMFA Open API Docs")
                        .description("IMFA - API 문서")
                        .version("1.0.0"));
    }*/

    /**
     * 개별 서버에서 제목/설명만 커스터마이징할 수 있도록 제공
     */
    public OpenAPI setOpenAPI(String title, String description, String version) {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version));
    }
}
