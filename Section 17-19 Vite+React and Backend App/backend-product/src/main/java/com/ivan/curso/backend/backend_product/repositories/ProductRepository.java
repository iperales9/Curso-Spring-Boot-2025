package com.ivan.curso.backend.backend_product.repositories;

import com.ivan.curso.backend.backend_product.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

// Mejor poner la dirección del front
@CrossOrigin("*")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {

}
