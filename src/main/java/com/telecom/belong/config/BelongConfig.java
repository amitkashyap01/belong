package com.telecom.belong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class BelongConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.telecom.belong"))
                .build()
                .apiInfo(belongApiDetails());
    }

    private ApiInfo belongApiDetails() {
        return new ApiInfo(
                "Belong - Phone API Documentation",
                "Phone APIs developed as part of Belong Coding Requirements",
                "1.0",
                "Use for code test purpose",
                new Contact("Amit Kashyap", "http://localhost:8080", "hello@belong"),
                "API Licence",
                "www.licenseurl.com", Collections.emptyList());
    }
}
