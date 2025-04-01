package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
