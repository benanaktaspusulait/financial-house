package com.fhouse.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Slf4j
@Configuration
@EnableSwagger2
@Profile({"test","dev"})
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(Collections.singletonList(new ApiKey("Authorization", "Authorization", "header")))
                .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "FHouse API DOCS",
                "FHouse API DOCS FOR TESTS",
                "v1.0",
                "Apache 2.0",
                new Contact("Benan Aktaş", "http://pusulait.com", "benan.aktas@pusulait.com"),
                "Apache 2.0",
                "Apache 2.0",
                Collections.emptyList()

        );
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, "Authorization", "");
    }

}