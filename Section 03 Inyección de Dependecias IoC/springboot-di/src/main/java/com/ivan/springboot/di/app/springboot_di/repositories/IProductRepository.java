package com.ivan.springboot.di.app.springboot_di.repositories;


import com.ivan.springboot.di.app.springboot_di.models.Product;

import java.util.List;

public interface IProductRepository {

    List<Product> findAll();

    Product findById(Long id);

}
