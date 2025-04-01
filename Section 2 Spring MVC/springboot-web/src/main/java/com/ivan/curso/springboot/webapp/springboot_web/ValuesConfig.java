package com.ivan.curso.springboot.webapp.springboot_web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/*
Por si quieres añadir mas de un archivo de configuración de propiedades
    @PropertySources({
        @PropertySource("classpath:values.properties"),
        @PropertySource("classpath:values.properties2")
})*/

@Configuration
@PropertySource(value = "classpath:values.properties", encoding = "UTF-8")
public class ValuesConfig {
}
