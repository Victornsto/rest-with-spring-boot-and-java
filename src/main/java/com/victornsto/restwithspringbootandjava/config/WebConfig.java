package com.victornsto.restwithspringbootandjava.config;

import com.victornsto.restwithspringbootandjava.serialization.converter.YamlJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public static final MediaType APPLICATION_YAML = MediaType.valueOf("application/yaml");
    @Value("${cors.originPatterns}")
    private String corsOriginPattern = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPattern.split(",");
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", APPLICATION_YAML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }
}
