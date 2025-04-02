package com.ivan.springboot.di.app.springboot_di.services;

import com.ivan.springboot.di.app.springboot_di.models.Product;
import com.ivan.springboot.di.app.springboot_di.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final IProductRepository repository;
    private final Environment environment;

    public ProductServiceImpl(@Qualifier("productJson") IProductRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        Double tax = environment.getProperty("config.price.tax", Double.class, 1.0); // Usa 1.0 como valor por defecto

        return repository.findAll().stream()
                .map(p -> {
                    Product newProd = (Product) p.clone();
                    Double priceTax = p.getPrice() * tax;
                    newProd.setPrice(Math.round(priceTax));
                    return newProd;
                })
                .toList();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}
