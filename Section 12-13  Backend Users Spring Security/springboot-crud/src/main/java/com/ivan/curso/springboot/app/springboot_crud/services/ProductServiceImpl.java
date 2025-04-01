package com.ivan.curso.springboot.app.springboot_crud.services;

import com.ivan.curso.springboot.app.springboot_crud.entities.Product;
import com.ivan.curso.springboot.app.springboot_crud.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {

        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            Product productDb = productOptional.orElseThrow();
            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());
            productDb.setSku(product.getSku());
            return Optional.of(repository.save(productDb));
        }

        return productOptional;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Product product) {

        Optional<Product> productOptional = repository.findById(product.getId());
        productOptional.ifPresent(repository::delete);
        return productOptional;

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existBySku(String sku) {
        return repository.existsBySku(sku);
    }

}
