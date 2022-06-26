package com.konstantakis.messages.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc Configuration to add the context interceptor
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    ContextInitializeInterceptor contextInitializeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextInitializeInterceptor);
    }
}
