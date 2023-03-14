package co.blog.config;

import co.blog.constants.ApiInfoSwaggerConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(getSecurityContext())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiKey apiKeys() {
        return new ApiKey(ApiInfoSwaggerConstants.API_KEY_NAME, ApiInfoSwaggerConstants.API_KEY_AUTHORIZATION_HEADER,
                ApiInfoSwaggerConstants.API_KEY_HEADER);
    }


    private List<SecurityContext> getSecurityContext() {
        return Arrays.asList(SecurityContext.builder().securityReferences(getSecurityReferences()).build());
    }

    private List<SecurityReference> getSecurityReferences() {

        AuthorizationScope scopes = new AuthorizationScope(ApiInfoSwaggerConstants.AUTHORIZATION_SCOPE,
                ApiInfoSwaggerConstants.AUTHORIZATION_DESCRIPTION);
        return Arrays.asList(new SecurityReference(ApiInfoSwaggerConstants.SECURITY_REFERENCE, new AuthorizationScope[] {scopes}));
    }

    private ApiInfo getInfo() {
        return new ApiInfo(ApiInfoSwaggerConstants.TITLE, ApiInfoSwaggerConstants.DESCRIPTION,
                ApiInfoSwaggerConstants.VERSION, ApiInfoSwaggerConstants.TERMS_OF_SERVICE_URL,
                new Contact(ApiInfoSwaggerConstants.NAME, ApiInfoSwaggerConstants.URL, ApiInfoSwaggerConstants.EMAIL),
                ApiInfoSwaggerConstants.LICENSE, ApiInfoSwaggerConstants.LICENSE_URL, ApiInfoSwaggerConstants.VENDOR_EXTENSIONS);
    }
}
