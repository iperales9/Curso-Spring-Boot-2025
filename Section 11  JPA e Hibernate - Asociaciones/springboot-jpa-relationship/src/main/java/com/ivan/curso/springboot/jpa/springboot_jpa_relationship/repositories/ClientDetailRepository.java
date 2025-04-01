package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetail;
import org.springframework.data.repository.CrudRepository;

public interface ClientDetailRepository extends CrudRepository<ClientDetail, Long> {
}
