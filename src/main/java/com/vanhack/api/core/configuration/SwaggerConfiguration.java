package com.vanhack.api.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket demoApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("VanHack Api")
                        .description("dev: www.linkedin.com/in/thiago-lima-04473b16 <-(I'm not that formal =x)")
                        .build())
                .select()
                .build();
    }
}
