package com.ivan.springboot.di.app.springboot_di.repositories;

import com.ivan.springboot.di.app.springboot_di.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@SessionScope
@RequestScope
*/

@Repository("productList")
public class ProductRepositoryImpl implements IProductRepository {

    private final List<Product> products;

    public ProductRepositoryImpl() {
        products = List.of(
                new Product(1L, "Laptop", 1000L),
                new Product(2L, "Mouse", 20L),
                new Product(3L, "Keyboard", 50L),
                new Product(4L, "Monitor", 200L),
                new Product(5L, "Headphones", 100L)
        );
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

}
