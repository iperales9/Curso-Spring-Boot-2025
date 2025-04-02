package com.ivan.curso.springboot.jpa.springboot_jpa;

import com.ivan.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.ivan.curso.springboot.jpa.springboot_jpa.entities.PersonDto;
import com.ivan.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repo;


    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        update();
    }

    @Transactional
    public void personalizedQueriesAggregation() {

        System.out.println("============= Consultas con el nombre mas corto   =============");
        Integer minNameLength = repo.getMinLengthName();
        System.out.println(minNameLength);

        System.out.println("============= Consultas con el nombre mas largo   =============");
        Integer maxNameLength = repo.getMaxLengthName();
        System.out.println(maxNameLength);

        Long count = repo.totalPerson();
        System.out.println("============= Consultas de total de registros de la tabla persona =============");
        System.out.println(count);

        Long min = repo.minId();
        System.out.println("============= Consultas con el valor minimo del id   =============");
        System.out.println(min);

        Long max = repo.maxId();
        System.out.println("============= Consultas con el valor maximo del id   =============");
        System.out.println(max);

        System.out.println("============= Consultas con el promedio de la longitud de los nombres de las personas =============");
        List<Object[]> regs = repo.getPersonLength();
        regs.forEach(reg -> System.out.println("Name: " + reg[0] + " length: " + reg[1]));

        System.out.println("============= Consultas resumen de funciones de agregacion min,max,sum,avg,count =============");
        Object[] resumenObj = (Object[]) repo.getResumeAggregationFuction();
        System.out.println("Min: " + resumenObj[0] + " Max: " + resumenObj[1] + " Sum: " + resumenObj[2] + " Avg: " + resumenObj[3] + " Count: " + resumenObj[4]);

    }


    @Transactional
    public void personalizedQueriesBetween() {
        System.out.println("============= Consultas por rangos =============");
        List<Person> persons = repo.findAllBetweenId(1, 5);
        persons.forEach(System.out::println);

        List<Person> persons2 = repo.findAllBetweenName("J", "Q");
        persons2.forEach(System.out::println);

    }

    @Transactional
    public void personalizedQueriesConcatUpperAndLowerCase() {

        System.out.println("============= Consultas con nombres de personas =============");
        List<String> names = repo.findAllFullNameConcat();
        names.forEach(System.out::println);

        System.out.println("============= Consultas con nombres de personas 2 =============");
        List<String> names2 = repo.findAllFullNameConcat2();
        names2.forEach(System.out::println);

        System.out.println("============= Consultas con nombres de personas en mayusculas =============");
        List<String> names3 = repo.findAllFullNameConcatUpper();
        names3.forEach(System.out::println);

        System.out.println("============= Consultas con nombres de personas en minusculas =============");
        List<String> names4 = repo.findAllFullNameConcatLower();
        names4.forEach(System.out::println);

        System.out.println("============= Consultas con nombres de personas en mayusculas y minusculas =============");
        List<String> names5 = repo.findAllNamesUpperAndLower();
        names5.forEach(System.out::println);
    }


    @Transactional
    public void personalizedQueriesDistinct() {

        System.out.println("============= Consultas con nombres de personas =============");
        List<String> names = repo.findAllNames();
        names.forEach(System.out::println);

        System.out.println("============= Consultas con nombres de personas distintos =============");
        List<String> names2 = repo.findAllNamesDistinct();
        names2.forEach(System.out::println);

        System.out.println("============= Consultas con total de lenguajes de programación unicas =============");
        Long totalLanguage = repo.findAllProgramingLanguageDistinctCount();
        System.out.println(totalLanguage);

    }

    @Transactional
    public void personalizedQuery() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============= Consulta el nobre por el id =======================");
        System.out.println("Ingrese el Id");
        Long id = scanner.nextLong();
        scanner.close();

        System.out.println("==== Mostrando solo el nombre ====");
        String name = repo.getNameById(id);
        System.out.println(name);
        System.out.println("==== Mostrando nombre completo con concat ====");
        String fullname = repo.getFullNameById(id);
        System.out.println(fullname);

        System.out.println("==== Consulta por campos personalizados por el id ====");
        Optional<Object> personReg = repo.obtenerPersonaDataById(id);
        personReg.ifPresent(p -> {
            Object[] person = (Object[]) p;
            System.out.println("Name: " + person[0] + " LastName: " + person[1] + " Programming Language: " + person[2]);
        });

        System.out.println("==== Consulta por campos personalizados lista ====");
        List<Object[]> persons = repo.obtenerPersonaDataList();
        persons.forEach(p -> System.out.println("Name: " + p[0] + " LastName: " + p[1] + " Programming Language: " + p[2]));

    }

    @Transactional
    public void personalizedQuery2() {
        System.out.println("============= Consulta por objeto persona y lenguaje de programación =======================");
        List<Object[]> persons = repo.findAllMixPersonDataList();
        persons.forEach(p -> System.out.println("Person: " + p[0] + " Programming Language: " + p[1]));

        System.out.println("============= Consulta que puebla y devuelve objeto entity de una instancia personalizada =======================");
        List<Person> persons2 = repo.findAllObjectPersonPersonalized();
        persons2.forEach(System.out::println);

        System.out.println("============= Consulta que puebla y devuelve objeto dto =======================");
        List<PersonDto> personDto = repo.findAllPersonDto();
        personDto.forEach(System.out::println);

    }


    @Transactional
    public void delete() {
        Scanner scanner = new Scanner(System.in);

        repo.findAll().forEach(System.out::println);
        System.out.println("Enter id");
        Long id = scanner.nextLong();
        scanner.nextLine();
        repo.deleteById(id);
        System.out.println("Deleted");
        repo.findAll().forEach(System.out::println);
        scanner.close();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Person> personOptional = repo.findById(id);
        personOptional.ifPresent(p -> {

            System.out.println(p);

            System.out.println("Ingrese el lenguaje de programacion");
            String programmingLanguage = scanner.nextLine();
            p.setProgrammingLanguage(programmingLanguage);
            Person persondb = repo.save(p);
            System.out.println("Updated");
            System.out.println(persondb);
        });

        scanner.close();

    }


    @Transactional
    public void crete() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter programming language");
        String programmingLanguage = scanner.nextLine();

        Person person = new Person(name, lastName, programmingLanguage);

        repo.save(person);
        System.out.println("Saved");
        repo.findById(person.getId()).ifPresent(System.out::println);

        scanner.close();
    }


    public void list() {
        //  List<Person> persons = (List<Person>) repo.findAll();
        //  List<Person> persons = (List<Person>) repo.findByProgrammingLanguage("Java");
        //  List<Person> persons = repo.buscarByProgrammingLanguageAndName("Java","John");
        //  List<Person> persons = repo.findByProgrammingLanguageAndName("Java","John");
        List<Object[]> persons = repo.optenerPersonaData();

        persons.forEach(p -> System.out.println("Name: " + p[0] + " Programming Language: " + p[1]));

        //  persons.forEach(System.out::println);
    }

    public void findOne() {
        //  repo.findById(1L).ifPresent(System.out::println);
        //  repo.findOne(1L).ifPresent(System.out::println);
        //  repo.findOneName("John").ifPresent(System.out::println);
        //  repo.findOneLikeName("Jo").ifPresent(System.out::println);
        //  repo.findByName("John").ifPresent(System.out::println);
        repo.findByNameContaining("John").ifPresent(System.out::println);
    }

}
