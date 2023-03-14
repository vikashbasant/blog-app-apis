package co.blog.config;

import co.blog.constants.ContentConfigConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ContentConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation (ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true)
                .parameterName(ContentConfigConstant.PARAMETER_NAME)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType(ContentConfigConstant.MEDIA_TYPE_JSON_EXTENSION, MediaType.APPLICATION_JSON)
                .mediaType(ContentConfigConstant.MEDIA_TYPE_XML_EXTENSION, MediaType.APPLICATION_XML);
    }
}
