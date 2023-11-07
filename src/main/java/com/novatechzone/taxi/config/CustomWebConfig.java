package com.novatechzone.taxi.config;

import com.novatechzone.taxi.dto.RequestMetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {
    @Autowired
    JWTInterceptor jwtInterceptor;

    public CustomWebConfig() {
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.jwtInterceptor);
    }

    @Bean
    @RequestScope
    public RequestMetaDTO getRequestMetaDTO() {
        return new RequestMetaDTO();
    }
}
