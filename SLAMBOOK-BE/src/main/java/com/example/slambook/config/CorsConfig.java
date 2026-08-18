package com.example.slambook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

@Configuration
public class
CorsConfig implements WebMvcConfigurer {

    @Value("${cors.allowed.origins:}")
    private String allowedOriginsStr;

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        var registration = registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
                
        if (StringUtils.hasText(allowedOriginsStr)) {
            String[] origins = allowedOriginsStr.split(",");
            for(int i = 0; i < origins.length; i++) {
                origins[i] = origins[i].trim();
            }
            registration.allowedOrigins(origins);
        }
    }
}
