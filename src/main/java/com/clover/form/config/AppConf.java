package com.clover.form.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConf implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9003")
                .allowedMethods("GET")
                .allowedMethods("PUT")
                .allowedMethods("DELETE")
                .allowedMethods("POST")
                .allowCredentials(true);
    }
}