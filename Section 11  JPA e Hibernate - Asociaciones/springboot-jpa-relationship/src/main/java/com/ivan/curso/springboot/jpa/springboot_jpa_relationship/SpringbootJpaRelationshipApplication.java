package com.ivan.curso.springboot.jpa.springboot_jpa_relationship;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.*;
import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final InvoiceRepository invoiceRepository;

    private final ClientDetailRepository clientDetailRepository;

    private final StudentsRepository studentsRepository;

    private final CourseRepository curseRepository;

    private static final Logger log = LoggerFactory.getLogger(SpringbootJpaRelationshipApplication.class);

    public SpringbootJpaRelationshipApplication(ClientRepository clientRepository, InvoiceRepository invoiceRepository,
                                                ClientDetailRepository clientDetailRepository, StudentsRepository studentsRepository,
                                                CourseRepository curseRepository) {
        this.clientRepository = clientRepository;
        this.invoiceRepository = invoiceRepository;
        this.clientDetailRepository = clientDetailRepository;
        this.studentsRepository = studentsRepository;
        this.curseRepository = curseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        manyToManyBidireccionalRemove();

    }

    @Transactional
    public void manyToManyBidireccionalRemove() {

        Student student1 = new Student("Fran", "Moras4");
        Student student2 = new Student("Juan", "Perales");

        Course curse1 = new Course("Curso Java", "Ivan");
        Course curse2 = new Course("Curso Spring", "Ivan");

        student1.addCourse(curse1);
        student1.addCourse(curse2);
        student2.addCourse(curse2);

        studentsRepository.saveAll(List.of(student1, student2));

        Optional<Student> studentOptionalDb = studentsRepository.findOneByIdCourse(3L);
        studentOptionalDb.ifPresent(student -> {
            Optional<Course> curseOptional = curseRepository.findById(3L);
            curseOptional.ifPresent(curse -> {
                student.getCurses().remove(curse);
                studentsRepository.save(student);
            });
        });


    }


    @Transactional
    public void manyToManyBidireccional() {
        Student student1 = new Student("Fran", "Moras4");
        Student student2 = new Student("Juan", "Perales");

        Course curse1 = new Course("Curso Java", "Ivan");
        Course curse2 = new Course("Curso Spring", "Ivan");

        student1.addCourse(curse1);
        student1.addCourse(curse2);
        student2.addCourse(curse2);

        studentsRepository.saveAll(List.of(student1, student2));

        log.info("Student 1 saved: {}", student1);
        log.info("Student 2 saved: {}", student2);

    }

    @Transactional
    public void manyToManyRemoveFindById() {


        // Faltan comprobaciones de si el Optional es vacío
        Student student1 = studentsRepository.findById(1L).get();
        Student student2 = studentsRepository.findById(2L).get();

        Course curse1 = curseRepository.findById(1L).get();
        Course curse2 = curseRepository.findById(2L).get();

        student1.setCurses(Set.of(curse1, curse2));
        student2.setCurses(Set.of(curse2));

        studentsRepository.saveAll(List.of(student1, student2));

        Optional<Student> studentOptionalDb = studentsRepository.findOneByIdCourse(1L);
        studentOptionalDb.ifPresent(student -> {
            Optional<Course> curseOptional = curseRepository.findById(1L);
            curseOptional.ifPresent(curse -> {
                student.getCurses().remove(curse);
                studentsRepository.save(student);
            });
        });


    }


    @Transactional
    public void manyToManyFindById() {

        // Faltan comprobaciones de si el Optional es vacío
        Student student1 = studentsRepository.findById(1L).get();
        Student student2 = studentsRepository.findById(2L).get();

        Course curse1 = curseRepository.findById(1L).get();
        Course curse2 = curseRepository.findById(2L).get();

        student1.setCurses(Set.of(curse1, curse2));
        student2.setCurses(Set.of(curse2));

        studentsRepository.saveAll(List.of(student1, student2));

    }


    @Transactional
    public void manyToMany() {
        Student student1 = new Student("Fran", "Moras4");
        Student student2 = new Student("Juan", "Perales");

        Course curse1 = new Course("Curso Java", "Ivan");
        Course curse2 = new Course("Curso Spring", "Ivan");

        student1.setCurses(Set.of(curse1, curse2));
        student2.setCurses(Set.of(curse2));

        studentsRepository.saveAll(List.of(student1, student2));

    }


    @Transactional
    public void oneToOneBidireccionalFindById() {

        Optional<Client> client = clientRepository.findOne(1L);
        ClientDetail clientDetail = new ClientDetail(true, 5000);

        client.ifPresent(c -> {
            c.setClientDetail(clientDetail);
            clientRepository.save(c);
        });

        log.info("Client saved Bidireccional by id: {}", client);

    }


    @Transactional
    public void oneToOneBidireccional() {

        Client client = new Client("Fran", "Perez");
        ClientDetail clientDetail = new ClientDetail(true, 5000);

        client.setClientDetail(clientDetail);
        clientRepository.save(client);

        log.info("Client saved Bidireccional : {}", client);

    }


    @Transactional
    public void oneToOne() {

        ClientDetail clientDetail = new ClientDetail(true, 5000);
        clientDetailRepository.save(clientDetail);

        Client client = new Client("Fran", "Perez");
        client.setClientDetail(clientDetail);
        clientRepository.save(client);

        log.info("Client saved : {}", client);

    }


    @Transactional
    public void oneToOneFindById() {

        ClientDetail clientDetail = new ClientDetail(true, 5000);
        clientDetailRepository.save(clientDetail);

        Optional<Client> optionalClient = clientRepository.findOne(1L);
        optionalClient.ifPresent(client -> {
            client.setClientDetail(clientDetail);
            clientRepository.save(client);
            log.info("Client saved 3: {}", client);

        });

    }


    @Transactional
    public void removeInvoiceBidireccionalFindById() {

        clientRepository.findOne(1L).ifPresent(client -> {

            log.info("Client: {}", client);

            Invoice invoice1 = new Invoice("Macbook Pro 2", 2000L);
            Invoice invoice2 = new Invoice("iPhone 13", 1000L);

            client.addInvoice(invoice1).addInvoice(invoice2);
            log.info("Client with invoices: {}", client);
            clientRepository.save(client);

        });

        Optional<Client> optionalClient = clientRepository.findOne(1L);

        optionalClient.ifPresent(client -> {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(1L);
            invoiceOptional.ifPresent(invoice -> {
                client.removeInvoice(invoice);
                clientRepository.save(client);
                log.info("Client with remove invoices: {}", client);

            });
        });

    }


    @Transactional
    public void oneToManyInvoiceBidireccionalFindById() {

        clientRepository.findOne(1L).ifPresent(client -> {

            log.info("Client 1: {}", client);

            Invoice invoice1 = new Invoice("Macbook Pro 2", 2000L);
            Invoice invoice2 = new Invoice("iPhone 12", 1000L);

            client.addInvoice(invoice1).addInvoice(invoice2);
            log.info("Client with invoices 2: {}", client);
            clientRepository.save(client);

        });

    }

    @Transactional
    public void oneToManyInvoiceBidireccional() {

        Client client = new Client("Fran", "Moras3");

        Invoice invoice1 = new Invoice("Macbook Pro", 2000L);
        Invoice invoice2 = new Invoice("iPhone 12", 1000L);

        client.addInvoice(invoice1).addInvoice(invoice2);

        invoice1.setClient(client);
        invoice2.setClient(client);

        clientRepository.save(client);

    }

    @Transactional
    public void removeAddressFindById() {

        clientRepository.findById(1L).ifPresent(client -> {

            Address address1 = new Address("Calle 12", 123);
            Address address2 = new Address("Calle 7", 456);

            Set<Address> addresses = new HashSet<>();
            addresses.add(address1);
            addresses.add(address2);
            client.setAddresses(addresses);
            clientRepository.save(client);

            log.info("Client saved address: {}", client);

            client.getAddresses().remove(address1);
            clientRepository.save(client);
            log.info("Client eliminate address: {}", client);

        });

    }


    @Transactional
    public void removeAddress() {

        Client client = new Client("Fran", "Moras");
        Address address1 = new Address("Calle 12", 123);
        Address address2 = new Address("Calle 7", 456);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);

        log.info("Client saved: {}", client);

        clientRepository.findById(5L).ifPresent(c -> {
            c.getAddresses().remove(address1);
            clientRepository.save(c);
            log.info("Client update: {}", c);
        });

    }

    @Transactional
    public void oneToMany() {

        Client client = new Client("Fran", "Moras");
        client.getAddresses().add(new Address("Calle 1", 123));
        client.getAddresses().add(new Address("Calle 2", 456));

        clientRepository.save(client);

    }

    @Transactional
    public void manyToOneFindById() {
        clientRepository.findById(1L).ifPresent(client -> {
            Set<Address> addresses = Set.of(
                    new Address("Calle 1", 123),
                    new Address("Calle 2", 456)
            );
            client.setAddresses(addresses);
            clientRepository.save(client);
        });
    }

    @Transactional
    public void manyToOneFindByIdClient() {

        Optional<Client> optionalClient = clientRepository.findById(1L);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            Invoice invoice = new Invoice("Macbook Pro", 2000L);
            invoice.setClient(client);
            Invoice invoiceDb = invoiceRepository.save(invoice);
            log.info("Invoice saved: {}", invoiceDb);
        }

    }

}
