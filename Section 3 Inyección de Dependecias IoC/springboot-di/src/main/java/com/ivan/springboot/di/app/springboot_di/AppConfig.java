package com.ivan.springboot.di.app.springboot_di;

import com.ivan.springboot.di.app.springboot_di.repositories.IProductRepository;
import com.ivan.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:product.json")
    private Resource resource;

    @Bean("productJson")
    @Primary
    public IProductRepository productRepositoryJson() {
        //return new ProductRepositoryJson();
        return new ProductRepositoryJson(resource);
    }

}
