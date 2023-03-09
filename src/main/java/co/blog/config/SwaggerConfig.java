package co.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo getInfo() {
        return new ApiInfo(ApiInfoSwaggerConstants.TITLE, ApiInfoSwaggerConstants.DESCRIPTION,
                ApiInfoSwaggerConstants.VERSION, ApiInfoSwaggerConstants.TERMS_OF_SERVICE_URL,
                new Contact(ApiInfoSwaggerConstants.NAME, ApiInfoSwaggerConstants.URL, ApiInfoSwaggerConstants.EMAIL),
                ApiInfoSwaggerConstants.LICENSE, ApiInfoSwaggerConstants.LICENSE_URL, ApiInfoSwaggerConstants.VENDOR_EXTENSIONS);
    }
}
