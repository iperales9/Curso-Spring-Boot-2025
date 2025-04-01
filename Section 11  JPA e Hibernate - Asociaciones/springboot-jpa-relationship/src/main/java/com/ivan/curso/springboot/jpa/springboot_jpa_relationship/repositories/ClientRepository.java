package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {

    @Query("SELECT c FROM Client c left JOIN FETCH c.addresses WHERE c.id = ?1")
    Optional<Client> findOneByIdAddress(Long id);


    @Query("SELECT DISTINCT c FROM Client c " +
            "LEFT JOIN FETCH c.invoices " +
            "WHERE c.id = ?1")
    Optional<Client> findOneByIdInvoice(Long id);

    @Query("SELECT DISTINCT c FROM Client c " +
            "LEFT JOIN FETCH c.invoices " +
            "LEFT JOIN FETCH c.addresses " +
            "LEFT JOIN FETCH c.clientDetail " +
            "WHERE c.id = ?1")
    Optional<Client> findOne(Long id);

}
