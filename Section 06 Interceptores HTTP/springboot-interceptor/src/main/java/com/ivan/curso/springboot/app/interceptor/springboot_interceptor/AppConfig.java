package com.ivan.curso.springboot.app.interceptor.springboot_interceptor;

import com.ivan.curso.springboot.app.interceptor.springboot_interceptor.interceptors.LoadingTimeInterceptors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Qualifier("timeInterceptor")
    private final LoadingTimeInterceptors loadingTimeInterceptors;

    public AppConfig(LoadingTimeInterceptors loadingTimeInterceptors) {
        this.loadingTimeInterceptors = loadingTimeInterceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loadingTimeInterceptors).addPathPatterns("/app/foo", "/app/vaz");
        registry.addInterceptor(loadingTimeInterceptors).excludePathPatterns("/app/bar");
    }


}
