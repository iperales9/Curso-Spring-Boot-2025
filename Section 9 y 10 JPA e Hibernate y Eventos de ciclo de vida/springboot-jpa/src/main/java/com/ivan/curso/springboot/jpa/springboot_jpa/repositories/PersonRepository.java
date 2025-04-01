package com.ivan.curso.springboot.jpa.springboot_jpa.repositories;

import com.ivan.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.ivan.curso.springboot.jpa.springboot_jpa.entities.PersonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query("select p.name from Person p where p.id = ?1")
    String getNameById(Long id);

    @Query("select concat(p.name, ' ', p.lastName) as fullname from Person p where p.id = ?1")
    String getFullNameById(Long id);

    @Query("SELECT p.name, p.lastName, p.programmingLanguage FROM Person p WHERE p.id = ?1")
    Optional<Object> obtenerPersonaDataById(Long id);

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findOne(Long id);

    Optional<Person> findByName(String id);

    Optional<Person> findByNameContaining(String id);

    @Query("select p from Person p where p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Person> buscarByProgrammingLanguageAndName(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p.id, p.name, p.programmingLanguage FROM Person p")
    List<Object[]> optenerPersonaData();

    @Query("SELECT p.name, p.lastName,p.programmingLanguage, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonaDataList();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1")
    List<Object[]> optenerPersonaDataByProgrammingLanguage(String programmingLanguage);

    // Clase 135

    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPersonDataList();

    @Query("select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    // Clase 136
    @Query("select new com.ivan.curso.springboot.jpa.springboot_jpa.entities.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    // Clase 137
    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgramingLanguageDistinctCount();

    // Clase 138

    @Query("select concat(p.name, ' ', p.lastName) from Person p ")
    List<String> findAllFullNameConcat();

    @Query("select (p.name || ' ' || p.lastName) FROM Person p")
    List<String> findAllFullNameConcat2();

    @Query("select (upper(p.name || ' ' || p.lastName)) from Person p ")
    List<String> findAllFullNameConcatUpper();

    @Query("select (lower(p.name || ' ' || p.lastName)) from Person p ")
    List<String> findAllFullNameConcatLower();

    @Query("select (lower(p.name) || ' ' || upper(p.lastName)) from Person p ")
    List<String> findAllNamesUpperAndLower();

    // Clase 139

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.id asc")
    List<Person> findAllBetweenId(Integer c1, Integer c2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc, p.lastName desc")
    List<Person> findAllBetweenName(String c1, String c2);

    // Clase 141
    List<Person>findByIdBetweenOrderByIdAsc(Integer c1, Integer c2);

    List<Person>findByNameBetweenOrderByNameDesc(String c1, String c2);

    // Clase 142

    @Query("select p from Person p order by p.name desc, p.lastName asc")
    List<Person> getAllOrder();

    // Clase 143

    @Query("select count(p) from Person p")
    Long totalPerson();
    @Query("select min(p.id) from Person p")
    Long minId();
    @Query("select max(p.id) from Person p")
    Long maxId();

    // Clase 144

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonLength();

    @Query("select min(length(p.name)) from Person p ")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p ")
    public Integer getMaxLengthName();

    // Clase 145

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)),count(p.id) from Person p")
    public Object getResumeAggregationFuction();

}
