package com.ivan.springboot.di.app.springboot_di.repositories;

import com.ivan.springboot.di.app.springboot_di.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepositoryFoo implements IProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor", 200L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Monitor", 200L);
    }

}
