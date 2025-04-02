package com.ivan.curso.springboot.di.factura.springboot_difactura;

import com.ivan.curso.springboot.di.factura.springboot_difactura.models.Item;
import com.ivan.curso.springboot.di.factura.springboot_difactura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    @Bean("default")
    List<Item> itemsInvoice() {
        Product p1 = new Product("Camara Sony", 800);
        Product p2 = new Product("Bicicleta Bianchi 26", 1200);
        return Arrays.asList(new Item(p1, 2), new Item(p2, 4));
    }

    @Bean()
    List<Item> itemsInvoiceOffice() {
        Product p1 = new Product("Monitor asus", 800);
        Product p2 = new Product("NoteBook", 2200);
        Product p3 = new Product("Keyboard", 200);
        Product p4 = new Product("Mouse", 150);
        return Arrays.asList(new Item(p1, 2), new Item(p2, 4), new Item(p3, 2), new Item(p4, 5));
    }
}